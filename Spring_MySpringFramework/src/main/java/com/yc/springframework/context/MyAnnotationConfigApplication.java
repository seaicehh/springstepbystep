package com.yc.springframework.context;

import com.yc.springframework.stereotype.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-05 11:41
 */
public class MyAnnotationConfigApplication implements MyApplicationContext{
    private Map<String,Object> beanMap=new HashMap<String, Object>();
    private Set<Class> manageBeanClasses=new HashSet<Class>();

    public  MyAnnotationConfigApplication(Class<?>... componentClasses) throws Exception {
        register(componentClasses);
    }

    //生成spring容器
    private void register(Class<?>[] componentClasses) throws Exception {
        if (componentClasses==null  || componentClasses.length<=0){
            throw  new RuntimeException("没有指定配置类");
        }
        for (Class cl:componentClasses){
            //@Configuration   //表示当前类是一个配置类
            //@ComponentScan(basePackages = "com.yc")   //将来要托管的bean 要扫描的包及子包
            System.out.println(cl.isAnnotationPresent(MyConfiguration.class));
            if (cl.isAnnotationPresent(MyConfiguration.class)==false){
                System.out.println(111);
                continue;
            }

            String[] basePackages=getAppConfigBasePackages();
            if (cl.isAnnotationPresent(MyComponentScan.class)){
                MyComponentScan mcs=(MyComponentScan) cl.getAnnotation(MyComponentScan.class);
                if (mcs.basePackages()!=null  && mcs.basePackages().length>0){
                    basePackages=mcs.basePackages();
                }

            }

            //处理@mybean的情况
            Object obj=cl.newInstance();
            handleAtMyBean(cl,obj);

            //处理  basePackages  基础包下的所有托管bean
            for (String basePackage :basePackages){
                scanPackageAndSubPackageClasses(basePackage);
            }

            //继续其他托管bean
            handleManagedBean();
            //循环beanMap中的每个bean，找到它们每个类中的每个由@Autowired@Resource注解的方法以实现di
            handleDi(beanMap);
        }
    }

    //处理managedBanClasses  所有的class类   筛选出所有的@component @server@repository的类  并实例化存到beanMap中
    private  void handleManagedBean() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for (Class c:manageBeanClasses){
            if (c.isAnnotationPresent(MyComponent.class)){
                saveManageBean(c);
            }else if (c.isAnnotationPresent(MyService.class)){
                saveManageBean(c);
            }else if (c.isAnnotationPresent(MyRepository.class)){
                saveManageBean(c);
            }else if (c.isAnnotationPresent(MyRepository.class)){
                saveManageBean(c);
            }else {

            }
        }

    }
//存储bean类
    private void saveManageBean(Class c) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Object o=c.newInstance();
        handlePostConstruct(o,c);
        String beanId=c.getSimpleName().substring(0,1).toLowerCase()+c.getSimpleName().substring(1);
        beanMap.put(beanId,o);
    }

    //查找file下面及其子包所有要托管的class，存到一个set（manageBeanClasses）中
    private  void findClassesInPackages(String file,String basePackage ) throws ClassNotFoundException {
        File f=new File(file);
        File[] classFiles=f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".class")  ||  file.isDirectory();
            }
        });
        System.out.println(classFiles);
        for (File cf:classFiles){
            if (cf.isDirectory()){
                //r如果是目录，则递归
                //拼接子目录
                basePackage="."+cf.getName().substring(cf.getName().lastIndexOf("/"),1);
                findClassesInPackages(cf.getAbsolutePath(),basePackage);
            }else {
                //加载cf   作为class文件
                URL[] urls=new URL[]{};
                URLClassLoader ucl=new URLClassLoader(urls);
                //
                Class c=ucl.loadClass(basePackage+"."+cf.getName().replace(".class",""));
                manageBeanClasses.add(c);
            }
        }
    }

    private void handleDi(Map<String, Object> beanMap) throws InvocationTargetException, IllegalAccessException {
        Collection<Object> objectCollection =beanMap.values();
        for (Object obj:objectCollection){
            Class cls=obj.getClass();
            Method[] ms=cls.getDeclaredMethods();
            for (Method m:ms){
                if (m.isAnnotationPresent(MyAutowired.class)  && m.getName().startsWith("set")){
                    //1.取出  m的参数类型
                    //2.从beanMap中循环所有object
                    //3.判断这些object是否为  参数类型的实例  instanceof
                    //4.如果是   则从beanMap中取出
                    //5。 invoke
                    //m.invoke(obj,)
                    invokeAutowiredMethod(m,obj);
                }else if (m.isAnnotationPresent(MyResource.class)   && m.getName().startsWith("set")){
                    //1.取出MyResource中的name属性值  当成备案Id
                    //2.如果没有  则取出  m方法中参数的类型名，改成首字母小写  当成beanId
                    //3.从beanMap中取出
                    //4.invoke
                    invokeResourceMethod(m,obj);
                }
            }
            Field[] fs=cls.getDeclaredFields();
            for (Field field:fs){
                if (field.isAnnotationPresent(MyAutowired.class)){

                }else if (field.isAnnotationPresent(MyResource.class)){

                }
            }
        }

    }

    //setStudent方法      bizImpl类
    private void invokeAutowiredMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1.取出  m的参数类型
        Class typeClass=m.getParameterTypes()[0];//studentDAO
        //2.从beanMap中循环所有object
        Set<String> keys=beanMap.keySet();
        for (String key:keys){
            //3.判断这些object是否为  参数类型的实例  instanceof(获取接口)
            Object o=beanMap.get(key);
            //4.如果是   则从beanMap中取出
            //o.getClass().getName()   ==    studenDAOIpaImpl        typeClass.getName()==studentDAo
//            if (o.getClass().getName().equalsIgnoreCase(typeClass.getName())){
//                m.invoke(obj,o);
//            }
            Class[] interfaces=o.getClass().getInterfaces();
            for (Class c:interfaces){
                System.out.println(c);
                if (c==typeClass){
                    m.invoke(obj,o);
                }
            }
        }
        //3.判断这些object是否为  参数类型的实例  instanceof
        //4.如果是   则从beanMap中取出
        //5。 invoke
        //m.invoke(obj,)
    }

    private void invokeResourceMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1.取出MyResource中的name属性值  当成备案Id
        MyResource mr=m.getAnnotation(MyResource.class);
        String beanId=mr.name();
        //2.如果没有  则取出  m方法中参数的类型名，改成首字母小写  当成beanId
        if (beanId==null  || beanId.equalsIgnoreCase("")){
            String pname=m.getParameterTypes()[0].getSimpleName();
            beanId=pname.substring(0,1).toLowerCase()+pname.substring(1);
        }
        //3.从beanMap中取出
        Object o=beanMap.get(beanId);
        //4.invoke
        m.invoke(obj,o);
    }

    private void scanPackageAndSubPackageClasses(String basePackage) throws IOException, ClassNotFoundException {
        String packagePath=basePackage.replaceAll("\\.","/");
        System.out.println("扫描包路径："+basePackage+",替换后："+packagePath);
        Enumeration<URL> files=Thread.currentThread().getContextClassLoader().getResources(packagePath);
        while (files.hasMoreElements()){
            URL url=files.nextElement();
            System.out.println("配置的扫描路径为："+url.getFile());
            //TODO: 递归这些目录  查找 。class文件
            findClassesInPackages(url.getFile(),basePackage);

        }
    }

    private void handleAtMyBean(Class cls, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1.获取cls中的所有method
        Method[] ms=cls.getDeclaredMethods();
        //2.循环，判断  每个method上是否有@mybean注解
        for (Method m:ms){

            if (m.isAnnotationPresent(MyBean.class)){
                //3.有  则invoke它，它返回值  存到beanmap中，键是方法名，值是返回值 对象
                Object o=m.invoke(obj);
                //TOTD:   加入处理   @MyBean注解对应的方法所实例化的类中的@MyPostConstruct 对应的方法
                handlePostConstruct(o,o.getClass());//o在这里指  helloWorld对象    o.getclass()它的反射对象
                beanMap.put(m.getName(),o);
            }
        }
    }

    //处理一个Mybena中的   @MyPostConstruct
    private void handlePostConstruct(Object o, Class<?> cls) throws InvocationTargetException, IllegalAccessException {
        Method[] ms=cls.getDeclaredMethods();
        for (Method m:ms){
            if (m.isAnnotationPresent(MyPostConstruct.class)){
                m.invoke(o);
            }
        }
    }

    //获取当前AppConfig类所在的包路径
    private String[] getAppConfigBasePackages(){
        String[] paths=new String[1];
        paths[0]= getClass().getPackage().getName();
        return paths;
    }
    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }


}
