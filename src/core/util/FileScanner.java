package core.util;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class FileScanner{
	private static String CLASS_SUFFIX = ".class";
	
	private static String EXCEPT_PACKAGE = "core";
	
    private static String defaultClassPath = FileScanner.class.getResource("/").getPath();
 
    public String getDefaultClassPath() {
        return defaultClassPath;
    }
 
    private  class ClassSearcher{
        private  Set<Class<?>> classPaths = new HashSet<>();
    	private  String MATCH_STR = CLASS_SUFFIX;
        public ClassSearcher(String endName) {
        	if(endName!=null)
        		this.MATCH_STR = endName + MATCH_STR;
		}
        private  Set<Class<?>> doPath(File file,String packageName,boolean flag) {
        	if(packageName.startsWith(EXCEPT_PACKAGE)){
        		return classPaths;
        	}
            if (file.isDirectory()) {
                //文件夹我们就递归
                File[] files = file.listFiles();
                if(!flag){
                	if(packageName.length()==0){
                		packageName = file.getName();
                	}else{
                		packageName = packageName+"."+file.getName();
                	}
                }
 
                for (File f1 : files) {
                    doPath(f1,packageName,false);
                }
            } else {//标准文件
                //标准文件我们就判断是否是class文件
                if (file.getName().endsWith(MATCH_STR)) {
                    //如果是class文件我们就放入我们的集合中。
                    try {
                        Class<?> clazz = Class.forName(packageName + "."+ file.getName().substring(0,file.getName().lastIndexOf(".")));
                        classPaths.add(clazz);
                    } catch (ClassNotFoundException e) {
                    }
                }
            }
            return classPaths;
        }
    }
    public Set<Class<?>> search(String packageName,String endName) {
        //先把包名转换为路径,首先得到项目的classpath
        String classpath = defaultClassPath;
        //然后把我们的包名basPack转换为路径名
        String basePackPath = packageName.replace(".", File.separator);
        String searchPath = classpath + basePackPath;
        return new ClassSearcher(endName).doPath(new File(searchPath),packageName,true);
    }
}
