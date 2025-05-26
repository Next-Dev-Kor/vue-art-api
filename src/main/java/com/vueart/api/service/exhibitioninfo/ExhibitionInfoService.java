package com.vueart.api.service.exhibitioninfo;

import com.vueart.api.dto.request.exhibitioninfo.ExhibitionInfoRequest;

public interface ExhibitionInfoService {
    void createExhibitionInfo(ExhibitionInfoRequest req);

    void updateExhibitionInfo(Long id, ExhibitionInfoRequest req);
}
