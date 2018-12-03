package net.anotheria.moskito.extensions;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.anotheria.moskito.core.plugins.AbstractMoskitoPlugin;
import net.anotheria.moskito.core.registry.ProducerRegistryFactory;
import net.anotheria.moskito.extensions.diskspacemonitoring.DiscSpaceMonitoringConfig;
import net.anotheria.moskito.extensions.diskspacemonitoring.DiscSpaceProducer;
import net.anotheria.moskito.extensions.diskspacemonitoring.DiscSpaceProducer.DiscSpaceInfoType;

/**
 * Disk space monitoring plugin.
 */
public class DiskSpaceMonitoringPlugin extends AbstractMoskitoPlugin {

	/**
	 * Logger.
	 */
	private static Logger log = LoggerFactory.getLogger(DiskSpaceMonitoringPlugin.class);


	@Override
	public void initialize() {
		DiscSpaceMonitoringConfig config = DiscSpaceMonitoringConfig.getInstance();
		String[] disks = config.getDisks();
		if (disks == null || disks.length == 0)
			return;
		for (Path root : FileSystems.getDefault().getRootDirectories()) {
			for (String disk : disks) {
				if (disk.equals(root.toString())) {
					ProducerRegistryFactory.getProducerRegistryInstance().registerProducer(new DiscSpaceProducer(root, DiscSpaceInfoType.AVAILABLE));
					ProducerRegistryFactory.getProducerRegistryInstance().registerProducer(new DiscSpaceProducer(root, DiscSpaceInfoType.TOTAL_SPACE));
					ProducerRegistryFactory.getProducerRegistryInstance().registerProducer(new DiscSpaceProducer(root, DiscSpaceInfoType.USED_PCT));
				} else {
					log.info("Found unknown disk in config: " + disk);
				}
			}
		}
	}


	@Override
	public void deInitialize() {
		super.deInitialize();
	}
}
