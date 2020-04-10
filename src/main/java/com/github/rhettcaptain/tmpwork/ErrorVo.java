package com.github.rhettcaptain.tmpwork;

import lombok.Data;

@Data
public class ErrorVo {
    private int checkId;
    private long lineNo;

    @Override
    public boolean equals(Object o) {
        return o instanceof ErrorVo && ((ErrorVo)o).getCheckId() == this.checkId && ((ErrorVo)o).lineNo == this.lineNo;
    }

}
