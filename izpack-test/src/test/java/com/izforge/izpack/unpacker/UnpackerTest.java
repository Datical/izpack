package com.izforge.izpack.unpacker;

import com.izforge.izpack.api.data.AutomatedInstallData;
import com.izforge.izpack.api.substitutor.VariableSubstitutor;
import com.izforge.izpack.installer.manager.PanelManager;
import com.izforge.izpack.installer.unpacker.IUnpacker;
import com.izforge.izpack.integration.AbstractIntegrationTest;
import com.izforge.izpack.matcher.ZipMatcher;
import com.izforge.izpack.util.IoHelper;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test of unpacker
 */
public class UnpackerTest extends AbstractIntegrationTest
{

    @Test
    public void testUnpackerPutUninstaller() throws Exception
    {
        compileInstallJar("basicInstall.xml", getWorkingDirectory("samples/basicInstall"));
        PanelManager panelManager = applicationContainer.getComponent(PanelManager.class);
        panelManager.loadPanelsInContainer().instanciatePanels();
        IUnpacker unpacker = applicationContainer.getComponent(IUnpacker.class);
        assertThat(unpacker, IsNull.notNullValue());

        unpacker.putUninstaller();

        AutomatedInstallData idata = applicationContainer.getComponent(AutomatedInstallData.class);
        VariableSubstitutor variableSubstitutor = applicationContainer.getComponent(VariableSubstitutor.class);
        String dest = IoHelper.translatePath(idata.getInfo().getUninstallerPath(), variableSubstitutor);
        String jar = dest + File.separator + idata.getInfo().getUninstallerName();
        File uninstallJar = new File(jar);
        assertThat(uninstallJar.exists(), Is.is(true));
        assertThat(uninstallJar, ZipMatcher.isZipContainingFiles("com/izforge/izpack/uninstaller/Destroyer.class", "langpack.xml"));

    }
}
