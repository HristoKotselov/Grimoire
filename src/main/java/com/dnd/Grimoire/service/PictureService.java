package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Picture;
import com.dnd.Grimoire.repository.PictureRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PictureService {

    @NonNull
    private final PictureRepository pictureRepository;

    @NonNull
    private final TagService tagService;

    public Optional<Picture> getPictureById(Long id) {
        return pictureRepository.findById(id);
    }

    public Picture createPicture(Picture picture) {
        if (picture.getTags() != null) {
            picture.setTags(tagService.getExistingTagsForEntityImport(picture.getTags()));
        }
        return pictureRepository.save(picture);
    }

    public void deletePicture(Picture picture) {
        pictureRepository.delete(picture);
    }

    public void deletePictureById(Long id) {
        pictureRepository.deleteById(id);
    }

    public long getTotalPictureCount() {
        return pictureRepository.count();
    }

    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }
}
