package sk.ygor.stackoverflow.q53295382;

import org.apache.commons.beanutils.*;
import org.apache.commons.beanutils.converters.DateConverter;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        DateConverter converter = new DateConverter();
        converter.setPattern("yyyy-MM-dd");

        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
        convertUtilsBean.register(converter, Date.class);

        BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());

        UserDto dto = new UserDto("42", "2018-11-14");
        System.out.println("dto = " + dto);

        UserModel model = new UserModel();
        beanUtilsBean.copyProperties(model, dto);

        System.out.println("model = " + model);
    }

}
