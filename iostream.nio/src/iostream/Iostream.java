/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iostream;

import java.io.*;
import java.nio.file.*;
import java.nio.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class Iostream {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		/*
		Reader (символьный), InputStream (байтовый)
		read
		skip
		mark / markSupported
		reset

		Writer, OutputStream
		write
		append
		flush
		close // AutoClosable -> Closable -> try(){}catch(){}

		--

		BufferedReader, BufferedInputStream
		BufferedWriter, BufferedOutputStream

		--

		PrintStream -- символьный вывод в консоль
		PrintWriter -- байтовый вывод в консоль

		--

		InputStreamReader - байтовый поток в символьный
		OutputSreamWriter - символьный поток в байтовый

		StreamTokenizer - разбивает входной символьный поток на элементы (токены)
		LineNumberReader - читает входной символьный поток построчно



		*/

		// вывод кириллицы в системную консоль
		try {
			OutputStreamWriter sw;
			sw = new OutputStreamWriter(System.out, "utf-8"); // windows-1251 // CP866
			PrintWriter pw = new PrintWriter(sw, true); // (поток вывода, флаг для сброса буфера после вывода println (не print!)
			pw.print("тестирование PrintWriter\n");
			pw.flush(); // вывод потока и сброс буфера

		// ввод кириллицы

			InputStreamReader isr = new InputStreamReader(System.in, "windows-1251");
			BufferedReader br = new BufferedReader(isr);

			if(false){
				p("enter text:");
				String inText = br.readLine();
				pw.printf("Введен текст: [%s] длинной %d символов. (именно %2$d)\n", inText, inText.length()); 
				pw.flush();
									// http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
				// Изменение порядка: %1$d, %2$d, ... 
				// %d - вывод целого десятичного числа. %o - восьмеричногою %x, %X - шестнадцатиричного. %#x
				// %10d, %-10d - выделение места под число. минус - смещение влево. %010d - ведущие нули. 
				// %+10d - вывод знака. % 10d - вместо "+" - пробел 
				// %b, %B - вывод boolean
				// %f, %e, %E, %2,5G, %g - дробные числа. после точки указывается точность.
				// e,E - с плавающей точкой.
				// f - с фиксированной точкой.
				// g,G - короткие с фиксированной, длинные с плавающей.
				// a, A - дробные шестнадцатиричные. без точности.
				// h,H - шестнадцатиричный хеш код объекта.
				// %c, %С - вывод символа
				// %s,%S - вывод строки
				// t, T - дата и время. значение - long | Long | Date | Calendar
				// %tH часы, %tM минуты, %tS секунды, L миллисекунды, N наносекунды
				// Y-m-d 

			}
			if(false){
				Console con = System.console();
				if(con != null){
					con.printf("enter to Console:\n");
					con.flush();
					String conIn = con.readLine();
					con.printf("введено: %s", conIn);
					con.flush();
				} else {
					p("Console is null");
				}
			}


			/*
			StringBuilder
				(int) // capacity
				(String)
				append()
				length()
				capasity()
				insert(int position, String str)
				delete(int begin , int end)
				deleteCharAt(index)
				replace(begin, end, String)
				reverce()

			StringTokenizer
				(str)
				(str, delimiters)
				(str, delimiters, flag) - bool flag:  включать ли в результат разделители



			*/

						// StringBuilder
						if(false){
							StringBuilder sbilder = new StringBuilder("SBF: ");
							sbilder.append("Append1. ");
							int len1 = sbilder.length();
							sbilder.append("Append2. ");
							sbilder.insert(len1, "Insert1. ");
							int len2 = sbilder.length();
							String del1 = "ToDel1. ";
							sbilder.append(del1);
							sbilder.append("AfterDel1. ");
							sbilder.delete(len2, len2 + del1.length());
							String resultOfSbf = sbilder.toString();
							p(resultOfSbf);

						}

						// StringTokenizer
			if(false){
							String src = " Вася Пупкин; 25 лет; неженат; слесарь; геймер";
							String delimiters = ";.-:%";
							StringTokenizer tok = new StringTokenizer(src, delimiters);

							//List skey = Arrays.asList(new String[]{"", ""});
							ArrayList<String> keys = new ArrayList<String>(Arrays.asList(new String[]
									{"имя", "возраст", "семейное положение", "специальность", "хобби"}));
							TreeMap<String, String> userData = new TreeMap<>();

							if(keys.size() == tok.countTokens()){
									// put
									for(String key: keys){
											if(!tok.hasMoreTokens()){
													pw.printf("Внезапное окончание параметров на ключе: %s\n", key);
													break;
											}
											userData.put(key, tok.nextToken());
									}
									//print
									for(String key: userData.keySet()){
											pw.printf("%20s : %-20s\n", key, userData.get(key));
									}
							} else {
									pw.printf("Несовпадение количества параметров.  keys: %d, values: %d", keys.size(), tok.countTokens());
							}
							pw.flush();
						}

		}catch(UnsupportedEncodingException e){
			//
		} catch (IOException ex) {
			//
		}
				if(true){
					fileIO();
				}

	}

	public static void fileIO(){
		try {
			// io
			File file = new File("C:\\\\tmp\\2.txt");

			FileReader fr = new FileReader(file);
			char[] cbuf = new char[128];
			fr.read(cbuf);
			String fileData = new String(cbuf);
			p(fileData);

			// nio
			FileSystem fs = FileSystems.getDefault();
			p(fs.getRootDirectories());
			p(fs.getSeparator());
			p(fs.isReadOnly());
			

		//    Path path = Files.createFile(Paths.get("C://1.txt"));
		} catch (IOException ex) {
			Logger.getLogger(Iostream.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
					/*
File()

NIO2 java.nio
FileSysten -> FileSystems
Path path = Path.get(“filename”);
for(Path name: path){}
Files. staticmethods(path)
	fileAttributes()
	setAttribute()
	getOwer()
	setOwner()
DirectorySream<Path> dir = new D..S..<>(path);
for(Path path: dir){}

new BufferedReader ( new FileInputStream(“1.txt”), “utf-8”  )
new BufferedWriter ( new FileOutputStream(“2.txt”), “koi-8” )


nio:
Buffer -> ByteBuffer, IntBuffer …
Channel -> ReadableByteChannel, WritableByteChannel, …
Channel.read(Buffer)
Fileas.readAllBytes(), ...
Files.newBufferedReader(Path, Charset)
Files.newInputStream(Path)
Files.newByteChannel()

DataOutputStream(FileOutputStream ) -> 
	writeByte, … writeUTF

DataInputStream(FileInputStream)

FileChennel fc = (FC) Files.newByteChennel(path, StanrardOpenOption.READ, StandardOpenOption.write);
	FileChenel.open(...)
raf = new RandomAccessFile(“1.txt”, “rw”);
ch = raf.newChennel();
seek()
	position()                
			*/

	public static void properties(){
		//InputStream istr = null;
		try(InputStream istr = new FileInputStream(new File("1.ini"))) {
			Properties prop = new Properties();
			prop.load(istr);
			p(prop.get("login"));
			p(prop.get("index"));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Iostream.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Iostream.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void p(Object x){
		System.out.println(x);
	}

}
