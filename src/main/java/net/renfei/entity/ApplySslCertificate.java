package net.renfei.entity;

import lombok.Data;

/**
 * <p>Title: ApplySslCertificate</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-02-11 21:06
 */
@Data
public class ApplySslCertificate {
    private String certificate;
    private String key;
    private String name;

    @Override
    public String toString() {
        return "ApplySslCertificate{" +
                "certificate='" + certificate + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
