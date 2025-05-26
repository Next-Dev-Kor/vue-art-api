package com.vueart.api.service.exhibitioninfo;

import com.vueart.api.dto.request.exhibitioninfo.ExhibitionInfoRequest;
import com.vueart.api.entity.ExhibitionInfo;
import com.vueart.api.repository.exhibitioninfo.ExhibitionInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExhibitionInfoServiceImpl implements ExhibitionInfoService {
    private final ExhibitionInfoRepository exhibitionInfoRepository;

    @Override
    @Transactional
    public void createExhibitionInfo(ExhibitionInfoRequest req) {

        ExhibitionInfo exhibitionInfo = ExhibitionInfo.builder()
                .title(req.title())
                .summary(req.summary())
                .description(req.description())
                .startDate(req.startDate())
                .endDate(req.endDate())
                .location(req.location())
                .isPresale(req.isPresale())
                .categoryId(req.categoryId())
                .build();

        exhibitionInfoRepository.save(exhibitionInfo);
    }

    @Override
    @Transactional
    public void updateExhibitionInfo(Long id, ExhibitionInfoRequest req) {
        ExhibitionInfo existing = exhibitionInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("전시 정보가 존재하지 않습니다. ID: " + id));

        ExhibitionInfo updated = existing.toBuilder()
                .title(req.title())
                .summary(req.summary())
                .description(req.description())
                .startDate(req.startDate())
                .endDate(req.endDate())
                .location(req.location())
                .isPresale(req.isPresale())
                .categoryId(req.categoryId())
                .build();

        exhibitionInfoRepository.save(updated);
    }
}
