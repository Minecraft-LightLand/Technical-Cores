package organize.sub;

import com.google.common.io.Files;
import organize.ResourceOrganizer;
import java.io.File;


public class RecipeFileOrganizer extends ResourceOrganizer {

    public RecipeFileOrganizer() {
        super(Type.DATA, "recipes", "recipes/");
    }

    @Override
    public void organize(File f) throws Exception {
        process("", f, (name, file) -> {
            String fs = getTargetFolder() + name;
            File ti = new File(fs + ".json");
            check(ti);
            Files.copy(file, ti);
        }, false);
    }

    private void process(String prefix, File f, ExcCons cons, boolean skip_dash) throws Exception {
        String filename = f.getName();
        if (skip_dash && filename.startsWith("-") || filename.startsWith("."))
            return;
        filename = f.isDirectory() ? filename : filename.split("\\.")[0];
        String name = filename.startsWith("_") ? prefix + filename : filename.endsWith("_") ? filename + prefix : filename;
        if (f.isDirectory()) {
            for (File fi : list(f)) {
                String file = fi.getName();
                if (file.startsWith("-") || file.startsWith("."))
                    continue;
                process(name, fi, cons, true);
            }
            return;
        }
        cons.accept(name, f);
    }

    private interface ExcCons {

        void accept(String name, File file) throws Exception;

    }

}
