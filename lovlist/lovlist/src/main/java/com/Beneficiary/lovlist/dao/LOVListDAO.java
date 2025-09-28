package com.Beneficiary.lovlist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

import java.math.BigDecimal;
import java.util.*;
import com.Beneficiary.lovlist.LOV.LOVList;
import com.Beneficiary.lovlist.LOV.LOVListSPR;

@Repository
public class LOVListDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public LOVListSPR getLOVList(int lovCode) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GET_LOV")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_MSG_ID", OracleTypes.VARCHAR),
                        new SqlParameter("P_DBG_MD", OracleTypes.NUMBER),
                        new SqlParameter("P_CHANNEL", OracleTypes.VARCHAR),
                        new SqlParameter("P_LOV_CODE", OracleTypes.NUMBER),
                        new SqlOutParameter("P_STATUS", OracleTypes.VARCHAR),
                        new SqlOutParameter("P_CURSOR", OracleTypes.CURSOR)
                );

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("P_MSG_ID", "uniqueeeemessage");
        inParams.put("P_DBG_MD", 0);
        inParams.put("P_CHANNEL", "test");
        inParams.put("P_LOV_CODE", lovCode);


        Map<String, Object> result = jdbcCall.execute(inParams);

        String status = (String) result.get("P_STATUS");

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> lovRecords = (List<Map<String, Object>>) result.get("P_CURSOR");

        List<LOVList> lovList = new ArrayList<>();
        for (Map<String, Object> row : lovRecords) {
            LOVList lov = new LOVList();
            lov.setLovCode((String) row.get("CODE"));
            lov.setLovName((String) row.get("NAME"));
            lovList.add(lov);
        }

        return new LOVListSPR(status, lovList);
    }
}