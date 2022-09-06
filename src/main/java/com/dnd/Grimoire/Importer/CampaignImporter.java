package com.dnd.Grimoire.Importer;

import com.dnd.Grimoire.model.Event;
import com.dnd.Grimoire.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CampaignImporter extends JPanel implements ActionListener {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080";

    ObjectMapper objectMapper = new ObjectMapper();

    Campaign campaign;
    Visibility dmVisibility;
    Visibility partyVisibilityOwnerDm;

    String tagNameSeparator = "_";
    String tagSeparator = "\\+";

    JButton go;
    JFileChooser chooser;
    String chooserTitle;

    public CampaignImporter() {
        go = new JButton("Do it");
        go.addActionListener(this);
        add(go);
//        objectMapper.registerSubtypes(
//                new NamedType(Item.class, "Item"),
//                new NamedType(Continent.class, "Continent"),
//                new NamedType(Region.class, "Region")
//        );
    }

    public void actionPerformed(ActionEvent e) {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(chooserTitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    + chooser.getSelectedFile());
            importCampaign(chooser);
        } else {
            System.out.println("No Selection ");
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame("");
        CampaignImporter panel = new CampaignImporter();
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        frame.getContentPane().add(panel, "Center");
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }

    private void importCampaign(JFileChooser chooser) {
        File campaign = chooser.getSelectedFile();
        if (campaign.isDirectory()) {

            File[] campaignObjects = campaign.listFiles();
            if (campaignObjects != null && campaignObjects.length > 0) {
                scanFolder(campaign);
                for (File file : campaignObjects) {
                    scanFolder(file);
                }
            } else {
                System.out.println("There must be a campaign as root!");
            }
        }
    }

    private void scanFolder(File file) {
        switch (file.getName()) {
            case "Items":
                importBaseEntity(file, Item.class, "item");
                break;
            case "Monsters":
                importBaseEntity(file, Monster.class, "monster");
                break;
            case "NPCs":
                importBaseEntity(file, Npc.class, "npc");
                break;
            case "PCs":
                importPc(file);
                break;
            case "Events":
                importBaseEntity(file, Event.class, "event");
                break;
            case "Planes":
                importBaseEntity(file, Plane.class, "plane");
                break;
            default:
                importCampaign(file.getName());
                break;
        }
    }

    private void importCampaign(String campaignName) {
        Account toniAccount = Account.builder().username("Tonkata").password("tonkata").build();
        Account icoAccount = Account.builder().username("Ickata").password("ickata").build();
        Account pepiAccount = Account.builder().username("Pepkata").password("pepkata").build();
        Account spasAccount = Account.builder().username("Spaskata").password("spaskata").build();
        restTemplate.postForObject(url + "/api/account/create", toniAccount, Account.class);
        restTemplate.postForObject(url + "/api/account/create", icoAccount, Account.class);
        restTemplate.postForObject(url + "/api/account/create", pepiAccount, Account.class);
        restTemplate.postForObject(url + "/api/account/create", spasAccount, Account.class);
        Account campaignOwner = restTemplate.getForObject(url + "/api/account/1", Account.class);
        Campaign newCampaign = Campaign.builder()
                .name(campaignName)
                .owner(campaignOwner)
                .build();
        campaign = restTemplate.postForObject(url + "/api/campaign/create", newCampaign, Campaign.class);
        dmVisibility = restTemplate.postForObject(url + "/api/visibility/campaign/dm", campaign, Visibility.class);
        partyVisibilityOwnerDm = restTemplate.postForObject(url + "/api/visibility/campaign/party", campaign, Visibility.class);
    }

    private void importBaseEntity(File file, Class<? extends BaseEntity> entityClass, String apiSpecifier) {
        for (File baseEntityDir : file.listFiles()) {
            File[] descAndPics = baseEntityDir.listFiles();
            File jsonFile = Arrays.stream(descAndPics).filter(json -> json.getName().contains(".json")).collect(Collectors.toList()).get(0);
            List<File> pics = Arrays.stream(descAndPics).filter(json -> !json.getName().contains(".json") && !json.isDirectory()).collect(Collectors.toList());
            List<File> folders = Arrays.stream(descAndPics).filter(File::isDirectory).collect(Collectors.toList());

            try {
                BaseEntity baseEntity = new ObjectMapper().readValue(jsonFile, entityClass);
                baseEntity.setCampaign(campaign);
                baseEntity.setName(baseEntityDir.getName());
                baseEntity.setVisibilities(List.of(dmVisibility));
                if (baseEntity.getAliases() != null) {
                    baseEntity.getAliases().forEach(alias -> alias.setVisibility(dmVisibility));
                }
                if (baseEntity.getDescriptions() != null) {
                    baseEntity.getDescriptions().forEach(description -> description.setVisibility(dmVisibility));
                }
                if (baseEntity.getTags() != null) {
                    baseEntity.getTags().forEach(tag -> tag.setVisibility(dmVisibility));
                }
                switch (apiSpecifier) {
                    case "monster":
                        if (((Monster) baseEntity).getLocations() != null) {
                            ((Monster) baseEntity).getLocations().forEach(location -> location.setVisibilities(List.of(dmVisibility)));
                        }
                        break;
                    case "npc":
                        if (((Npc) baseEntity).getAffiliations() != null) {
                            ((Npc) baseEntity).getAffiliations().forEach(affiliation -> {
                                affiliation.setVisibility(dmVisibility);
                                if (affiliation.getAliases() != null) {
                                    affiliation.getAliases().forEach(alias -> alias.setVisibility(dmVisibility));
                                }
                            });
                        }
                        if (((Npc) baseEntity).getLocations() != null) {
                            ((Npc) baseEntity).getLocations().forEach(location -> location.setVisibilities(List.of(dmVisibility)));
                        }
                        if (((Npc) baseEntity).getGender() != null) {
                            ((Npc) baseEntity).getGender().setVisibilities(List.of(dmVisibility));
                        }
                        break;
                    case "item":
                        if (((Item) baseEntity).getItemValue() != null) {
                            ((Item) baseEntity).getItemValue().setVisibilities(List.of(dmVisibility));
                        }
                        if (((Item) baseEntity).getAttunement() != null) {
                            ((Item) baseEntity).getAttunement().setVisibilities(List.of(dmVisibility));
                        }
                        if (((Item) baseEntity).getRarity() != null) {
                            ((Item) baseEntity).getRarity().setVisibilities(List.of(dmVisibility));
                        }
                        break;
                    case "event":
                        if (((Event) baseEntity).getDate() != null) {
                            ((Event) baseEntity).getDate().setVisibilities(List.of(dmVisibility));
                        }
                        break;
                }
                BaseEntity createdBaseEntity = restTemplate.postForObject(url + "/api/" + apiSpecifier + "/create", entityClass.cast(baseEntity), entityClass);

                for (File pic : pics) {
                    String fileNameWithoutExtension = pic.getName().split("\\.")[0];
                    String[] nameAndTags = fileNameWithoutExtension.split(tagNameSeparator);
                    String picLabel = nameAndTags[0];
                    List<Tag> tags = new ArrayList<>();

                    if (nameAndTags.length > 1) {
                        String[] tagNames = nameAndTags[1].split(tagSeparator);
                        for (String tagName : tagNames) {
                            Tag tag = Tag.builder().name(tagName).build();
                            tags.add(tag);
                        }
                        tags.forEach(tag -> tag.setVisibility(dmVisibility));
                    }

                    Picture picture = Picture.builder()
                            .baseEntity(createdBaseEntity)
                            .filepath(pic.getAbsolutePath())
                            .label(picLabel)
                            .tags(tags)
                            .visibilities(List.of(dmVisibility))
                            .build();

                    Picture createdPicture = restTemplate.postForObject(url + "/api/picture/create", picture, Picture.class);

//                    List<Tag> createdTags = restTemplate.postForObject(url + "/api/tag/create", picture, Picture.class);
                }

                if (apiSpecifier.equalsIgnoreCase("plane")) {
                    for (File folder : folders) {
                        importPlace(folder, Continent.class, "continent", createdBaseEntity);
                    }
                }
            } catch (IOException e) {
                System.out.println("Could not parse base entity object.");
                System.out.println(e.getMessage());
            }
        }
    }

    private void importPlace(File file, Class<? extends BaseEntity> entityClass, String apiSpecifier, BaseEntity parentBaseEntity) {
        File[] descAndPics = file.listFiles();
        File jsonFile = Arrays.stream(descAndPics).filter(json -> json.getName().contains(".json")).collect(Collectors.toList()).get(0);
        List<File> pics = Arrays.stream(descAndPics).filter(json -> !json.getName().contains(".json") && !json.isDirectory()).collect(Collectors.toList());
        List<File> folders = Arrays.stream(descAndPics).filter(File::isDirectory).collect(Collectors.toList());

        try {
            BaseEntity baseEntity = new ObjectMapper().readValue(jsonFile, entityClass);

            if (entityClass.equals(Continent.class)) {
                ((Continent) baseEntity).setPlane((Plane) parentBaseEntity);
            } else if (entityClass.equals(Region.class)) {
                ((Region) baseEntity).setContinent((Continent) parentBaseEntity);
            } else if (entityClass.equals(Settlement.class)) {
                ((Settlement) baseEntity).setRegion((Region) parentBaseEntity);
            } else if (entityClass.equals(District.class)) {
                ((District) baseEntity).setSettlement((Settlement) parentBaseEntity);
            } else if (entityClass.equals(Place.class)) {
                ((Place) baseEntity).setDistrict((District) parentBaseEntity);
            }

            baseEntity.setCampaign(campaign);
            baseEntity.setName(file.getName());
            baseEntity.setVisibilities(List.of(dmVisibility));
            if (baseEntity.getDescriptions() != null) {
                baseEntity.getDescriptions().forEach(description -> description.setVisibility(dmVisibility));
            }
            if (baseEntity.getTags() != null) {
                baseEntity.getTags().forEach(tag -> tag.setVisibility(dmVisibility));
            }
            BaseEntity createdBaseEntity = restTemplate.postForObject(url + "/api/" + apiSpecifier + "/create", entityClass.cast(baseEntity), entityClass);

            for (File pic : pics) {
                String fileNameWithoutExtension = pic.getName().split("\\.")[0];
                String[] nameAndTags = fileNameWithoutExtension.split(tagNameSeparator);
                String picLabel = nameAndTags[0];
                List<Tag> tags = new ArrayList<>();

                if (nameAndTags.length > 1) {
                    String[] tagNames = nameAndTags[1].split(tagSeparator);
                    for (String tagName : tagNames) {
                        Tag tag = Tag.builder().name(tagName).build();
                        tags.add(tag);
                    }
                    tags.forEach(tag -> tag.setVisibility(dmVisibility));
                }

                Picture picture = Picture.builder()
                        .baseEntity(entityClass.cast(createdBaseEntity))
                        .filepath(pic.getAbsolutePath())
                        .label(picLabel)
                        .tags(tags)
                        .visibilities(List.of(dmVisibility))
                        .build();

                Picture createdPicture = restTemplate.postForObject(url + "/api/picture/create", picture, Picture.class);
            }

            if (folders.size() > 0) {
                for (File folder : folders) {
                    switch (apiSpecifier) {
                        case "plane":
                            importPlace(folder, Continent.class, "continent", createdBaseEntity);
                            break;
                        case "continent":
                            importPlace(folder, Region.class, "region", createdBaseEntity);
                            break;
                        case "region":
                            importPlace(folder, Settlement.class, "settlement", createdBaseEntity);
                            break;
                        case "settlement":
                            importPlace(folder, District.class, "district", createdBaseEntity);
                            break;
                        case "district":
                            importPlace(folder, Place.class, "place", createdBaseEntity);
                            break;
                        default:
                            System.out.println("Importing '" + apiSpecifier + "' not identified");
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not parse base entity object.");
            System.out.println(e.getMessage());
        }
    }

    private void importPc(File file) {
        for (File baseEntityDir : file.listFiles()) {
            File[] descAndPics = baseEntityDir.listFiles();
            File jsonFile = Arrays.stream(descAndPics).filter(json -> json.getName().contains(".json")).collect(Collectors.toList()).get(0);
            List<File> pics = Arrays.stream(descAndPics).filter(json -> !json.getName().contains(".json") && !json.isDirectory()).collect(Collectors.toList());

            try {
                Pc pc = new ObjectMapper().readValue(jsonFile, Pc.class);
                pc.setCampaign(campaign);
                pc.setName(baseEntityDir.getName());
                pc.getRace().setVisibilities(List.of(dmVisibility));
                if (pc.getAliases() != null) {
                    pc.getAliases().forEach(alias -> alias.setVisibility(dmVisibility));
                }
                if (pc.getDescriptions() != null) {
                    pc.getDescriptions().forEach(description -> description.setVisibility(dmVisibility));
                }
                if (pc.getTags() != null) {
                    pc.getTags().forEach(tag -> tag.setVisibility(dmVisibility));
                }
                if (pc.getAffiliations() != null) {
                    pc.getAffiliations().forEach(affiliation -> {
                        affiliation.setVisibility(dmVisibility);
                        if (affiliation.getAliases() != null) {
                            affiliation.getAliases().forEach(alias -> alias.setVisibility(dmVisibility));
                        }
                    });
                }
                if (pc.getGender() != null) {
                    pc.getGender().setVisibilities(List.of(partyVisibilityOwnerDm));
                }
                Pc createdPc = restTemplate.postForObject(url + "/api/pc/create", pc, Pc.class);

                for (File pic : pics) {
                    String fileNameWithoutExtension = pic.getName().split("\\.")[0];
                    String[] nameAndTags = fileNameWithoutExtension.split(tagNameSeparator);
                    String picLabel = nameAndTags[0];
                    List<Tag> tags = new ArrayList<>();

                    if (nameAndTags.length > 1) {
                        String[] tagNames = nameAndTags[1].split(tagSeparator);
                        for (String tagName : tagNames) {
                            Tag tag = Tag.builder().name(tagName).build();
                            tags.add(tag);
                        }
                        tags.forEach(tag -> tag.setVisibility(dmVisibility));
                    }

                    Picture picture = Picture.builder()
                            .pc(createdPc)
                            .filepath(pic.getAbsolutePath())
                            .label(picLabel)
                            .tags(tags)
                            .visibilities(List.of(dmVisibility))
                            .build();

                    Picture createdPicture = restTemplate.postForObject(url + "/api/picture/create", picture, Picture.class);
                }
            } catch (IOException e) {
                System.out.println("Could not parse pc object.");
                System.out.println(e.getMessage());
            }
        }
    }

}
