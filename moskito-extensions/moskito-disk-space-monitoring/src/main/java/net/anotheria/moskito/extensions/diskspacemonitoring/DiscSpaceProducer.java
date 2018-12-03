package net.anotheria.moskito.extensions.diskspacemonitoring;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.anotheria.moskito.core.predefined.MemoryStats;
import net.anotheria.moskito.core.producers.IStats;
import net.anotheria.moskito.core.producers.IStatsProducer;
import net.anotheria.moskito.core.util.BuiltinUpdater;

/**
 * DiscSpace producer.
 */
public class DiscSpaceProducer implements IStatsProducer {
	/**
	 * Logger.
	 */
	private static Logger log = LoggerFactory.getLogger(DiscSpaceProducer.class);
	/**
	 * The id of the producer.
	 */
	private String path;
	/**
	 * The id of the producer.
	 */
	private String producerId;
	/**
	 * Stats.
	 */
	private MemoryStats stats;
	/**
	 * Cached stats list.
	 */
	private List<IStats> statsList;

	private static final String AVAILABLE_ID = "DiscSpaceUsable on - ";
	private static final String TOTAL_ID = "DiscSpaceTotal on - ";
	private static final String USED_PCT = "DiscSpaceUsedPct on - ";

	private FileStore root;

	private DiscSpaceInfoType discInfoType;

	public enum DiscSpaceInfoType {
		AVAILABLE,
		TOTAL_SPACE,
		USED_PCT;
	}


	public DiscSpaceProducer(Path rootPath, DiscSpaceInfoType discInfoType) {
		path = rootPath.toString();

		this.discInfoType = discInfoType;
		switch (discInfoType) {
			case USED_PCT:
				producerId = USED_PCT + path;
				break;
			case TOTAL_SPACE:
				producerId = TOTAL_ID + path;
				break;
			case AVAILABLE:
			default:
				producerId = AVAILABLE_ID + path;
				break;
		}

		statsList = new CopyOnWriteArrayList<IStats>();
		stats = new MemoryStats(producerId);
		statsList.add(stats);

		try {
			root = Files.getFileStore(rootPath);
		} catch (IOException e) {
			log.error("Querying space. Init error: " + e.toString());
			return;
		}

		BuiltinUpdater.addTask(new TimerTask() {
			@Override
			public void run() {
				readMemory();
			}
		});
	}


	@Override
	public String getCategory() {
		return "memory";
	}


	@Override
	public String getProducerId() {
		return producerId;
	}


	@Override
	public List<IStats> getStats() {
		return statsList;
	}


	@Override
	public String getSubsystem() {
		return "plugins";
	}


	/**
	 * Reads the memory value from the resolver and updates internal stats.
	 */
	private void readMemory() {
		long space;
		try {
			switch (discInfoType) {
				case USED_PCT:
					space = new BigDecimal(root.getUsableSpace()).multiply(new BigDecimal(100)).divide(new BigDecimal(root.getTotalSpace()), 0).longValue();
					break;
				case TOTAL_SPACE:
					space = root.getTotalSpace();
					break;
				case AVAILABLE:
				default:
					space = root.getUsableSpace();
					producerId = AVAILABLE_ID + path;
					break;
			}
		} catch (IOException e) {
			log.error("Querying space. Querying error: " + e.toString());
			return;
		}
		stats.updateMemoryValue(space);
	}


	@Override
	public String toString() {
		return super.toString() + ' ' + this.getClass().getSimpleName();
	}
}
