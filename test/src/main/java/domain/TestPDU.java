package domain;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * Created by DELL on 2017/7/4.
 */
@Data
public class TestPDU {

    @NotBlank(message = "test1 cannot be blank")
    @NotNull(message = "test1 cannot be null")
    private String test1;

    private String test2;
}
