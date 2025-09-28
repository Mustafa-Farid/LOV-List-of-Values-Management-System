package com.Beneficiary.lovlist.service;

import com.Beneficiary.lovlist.LOV.LOVListSPR;
import com.Beneficiary.lovlist.dao.LOVListDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LOVService {
    @Autowired
    private LOVListDAO lovListRepository;

    public LOVListSPR getLovList(int lovCode) {
        return lovListRepository.getLOVList(lovCode);

    }
}
