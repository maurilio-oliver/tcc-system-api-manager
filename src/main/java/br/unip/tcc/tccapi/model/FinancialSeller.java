package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class FinancialSeller extends GenericJsonConverter<FinancialSeller> {
    private String bankCode, branchCode, accountNumber;

    @Override
    public String getTypeName(){
        return Financial.class.getTypeName();
    }

}
