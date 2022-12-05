package XuLi;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class CheckFileChange {
	public static void main(String[] args) throws IOException {
		WatchService watcher = FileSystems.getDefault().newWatchService();
		Path dir = Paths.get("E:\\TestPBL4\\User\\Tuan");
		dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

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
				System.out.println(kind.name() + ": " + fileName);
				if (kind == ENTRY_MODIFY && fileName.toString().equals("DirectoryWatchDemo.java")) {
					System.out.println("My source file has changed!!!");
				} else if (kind == ENTRY_CREATE) {
					System.out.println("create");
				} else if (kind == ENTRY_DELETE) {
					System.out.println("delete");
				} else if (kind == ENTRY_MODIFY) {
					System.out.println("modify");
				}
			}
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
	}
}
