package projects;

import org.embl.mobie.io.ImageDataFormat;
import org.embl.mobie.viewer.MoBIE;
import org.embl.mobie.viewer.MoBIESettings;
import net.imagej.ImageJ;

import java.io.IOException;

public class OpenRemoteBdvOmeZarr {
    public static void main(String[] args) throws IOException {
        final ImageJ imageJ = new ImageJ();
        imageJ.ui().showUI();
        new MoBIE("https://s3.embl.de/i2k-2020/project-bdv-ome-zarr", MoBIESettings.settings().imageDataFormat( ImageDataFormat.BdvOmeZarr));
    }
}