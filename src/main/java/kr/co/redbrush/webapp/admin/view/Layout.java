package kr.co.redbrush.webapp.admin.view;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.Data;

import java.io.IOException;
import java.io.Writer;

@Data
public class Layout implements Mustache.Lambda {
    private String header;
    private String content;
    private String scripts;

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {

    }
}
