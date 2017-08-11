package com.zhongda.quote.machine.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
/**
 * <p>机器码。</p>
 * @author rojay<1250368725@qq.com>
 * @since 2017年8月11日
 */
public class SequenceServiceImpl extends AbstractSequenceService{
	/**
	 * 获取机器码
	 * @return
	 * 		机器码
	 */
	public static String getMachineCode(){
		SequenceServiceImpl se = new SequenceServiceImpl();
		return se.getSequence();
	}
	    @Override 
	    public String getSequence() {
		 	
	        String cpuID=getCPUSerial(); 
	        String hdID=getHDSerial("C"); 
	        if(cpuID==null || hdID==null){ 
	            return null; 
	        } 
	       String machineCode = getMD5(cpuID+hdID); 
	        return machineCode.substring(2, 17); 
	    } 
	     
	    /**
	     *
	     * @param drive 硬盘驱动器分区 如C,D
	     * @return 该分区的卷标
	     */ 
	    private String getHDSerial(String drive) { 
	        StringBuilder result = new StringBuilder(); 
	        try { 
	            File file = File.createTempFile("tmp", ".vbs"); 
	            file.deleteOnExit(); 
	            try (FileWriter fw = new java.io.FileWriter(file)) { 
	                String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n" 
	                        + "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive + "\")\n" 
	                        + "Wscript.Echo objDrive.SerialNumber"; 
	                fw.write(vbs); 
	            } 
	            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath()); 
	            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) { 
	                String line; 
	                while ((line = input.readLine()) != null) { 
	                    result.append(line); 
	                } 
	            } 
	            file.delete(); 
	        } catch (Throwable e) { 
	            System.out.println("生成HDSerial失败" + e); 
	        } 
	        if (result.length() < 1) { 
	            System.out.println("无磁盘ID被读取"); 
	        } 
	 
	        return result.toString(); 
	    } 
	 
	    /**
	     * 获取CPU号,多CPU时,只取第一个
	     * @return
	     */ 
	    private String getCPUSerial() { 
	        StringBuilder result = new StringBuilder(); 
	        try { 
	            File file = File.createTempFile("tmp", ".vbs"); 
	            file.deleteOnExit(); 
	            try (FileWriter fw = new FileWriter(file)) { 
	                String vbs = "On Error Resume Next \r\n\r\n" + "strComputer = \".\"  \r\n" 
	                        + "Set objWMIService = GetObject(\"winmgmts:\" _ \r\n" 
	                        + "    & \"{impersonationLevel=impersonate}!\\\\\" & strComputer & \"\\root\\cimv2\") \r\n" 
	                        + "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_Processor\")  \r\n " 
	                        + "For Each objItem in colItems\r\n " + "    Wscript.Echo objItem.ProcessorId  \r\n " 
	                        + "    exit for  ' do the first cpu only! \r\n" + "Next                    "; 
	 
	                fw.write(vbs); 
	            } 
	            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath()); 
	            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) { 
	                String line; 
	                while ((line = input.readLine()) != null) { 
	                    result.append(line); 
	                } 
	            } 
	            file.delete(); 
	        } catch (Throwable e) { 
	            System.out.println("生成CPUSerial失败"+ e); 
	        } 
	        if (result.length() < 1) { 
	           System.out.println("无CPU_ID被读取"); 
	        } 
	        return result.toString(); 
	    } 
	     
	   /* public static void main(String[] args) {         
	        SequenceService s = new SequenceServiceImpl(); 
	        String seq = s.getSequence(); 
	        System.out.println(seq.substring(0, 16)); 
	    } */
	}  