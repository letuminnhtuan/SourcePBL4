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

import ClassObj.ObjInfor;
import GUI.fMain;

public class CheckFileEdit extends Thread {
	public String path;
	public fMain f;

	public CheckFileEdit(String path, fMain form) {
		this.path = path;
		this.f = form;
	}

	@Override
	public void run() {
		try {
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
					if (kind == ENTRY_MODIFY) {
						File file = new File(path + "\\" + fileName.getFileName());
						System.out.println(this.f.user.name + ": " + file.getAbsolutePath());
//						ObjInfor obj = new ObjInfor(file, this.f.user, "now", "upload,");
//						this.f.dataOutput.writeObject(obj);
					}
				}
				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}