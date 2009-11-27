package com.izforge.izpack.installer.provider;

import com.izforge.izpack.installer.bootstrap.GUIInstaller;
import com.izforge.izpack.installer.data.InstallData;
import org.picocontainer.injectors.Provider;

/**
 * Provide Gui Installer instance
 */
public class GUIInstallerProvider implements Provider {

    public GUIInstaller provide(InstallData installData) throws Exception {
        GUIInstaller installer = new GUIInstaller(installData);
        installer.initData();
        return installer;
    }

    

}
