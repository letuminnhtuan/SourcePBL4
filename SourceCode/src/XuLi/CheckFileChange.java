package XuLi;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class CheckFileChange {
	public CheckFileChange(String path) throws IOException {
		WatchService watcher = FileSystems.getDefault().newWatchService();
		Path dir = Paths.get(path);
		dir.register(watcher, ENTRY_MODIFY);
		while (true) {
			WatchKey key;
			try {
				key = watcher.take();
			} catch (InterruptedException ex) {
				return;
			}
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();
				@SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>) event;
				Path fileName = ev.context();
				File file = new File(path + "\\"+ fileName.getFileName());
			}
			
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new CheckFileChange("E:\\TestPBL4\\User\\Tuan");
	}
}