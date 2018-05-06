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

    private Mustache.Compiler compiler;

    public Layout() {

    }

    public Layout(Mustache.Compiler compiler) {
        this.compiler = compiler;
    }

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        header = frag.execute();
        content = frag.execute();
        scripts = frag.execute();

        // TODO : Implement execute
        /*
        compiler.compile("{{>layout}}").execute(frag.context(), out);
        */
    }
}
