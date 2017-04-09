package csg;

import csg.data.CSData;
import csg.file.CSFiles;
import csg.style.CSStyle;
import csg.workspace.CSWorkspace;
import djf.AppTemplate;
import java.util.Locale;
import static javafx.application.Application.launch;

/**
 *
 * @author tyx
 */
public class CSGeneratorApp extends AppTemplate{

    @Override
    public void buildAppComponentsHook() {
        dataComponent = new CSData(this);
        workspaceComponent = new CSWorkspace(this);       
        fileComponent = new CSFiles(this);
        styleComponent = new CSStyle(this);
    }
    
    public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	launch(args);
    }
}
