package com.fred.longd.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.io.PrintWriter;

/**
 * @author Fred Liu(liuxiaokun0410@gmail.com)
 * @version 1.0
 * @date 2015/9/18
 */
public class UpgradeUtil {


    public static boolean install(String apkPath, Context context) {
        // 先判断手机是否有root权限
        if (hasRootPerssion()) {
            // 有root权限，利用静默安装实现
            return clientInstall(apkPath);
        } else {
            // 没有root权限，利用意图进行安装
            File file = new File(apkPath);
            if (!file.exists())
                return false;
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.startActivity(intent);
            return true;
        }
    }

    private static boolean clientInstall(String apkPath) {
        PrintWriter PrintWriter = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            PrintWriter = new PrintWriter(process.getOutputStream());
            PrintWriter.println("chmod 777 " + apkPath);
            PrintWriter.println("export LD_LIBRARY_PATH=/vendor/lib:/system/lib");
            PrintWriter.println("pm install -r " + apkPath);
//          PrintWriter.println("exit");
            PrintWriter.flush();
            PrintWriter.close();
            int value = process.waitFor();
            return returnResult(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return false;
    }

    private static boolean hasRootPerssion() {
        PrintWriter PrintWriter = null;
        Process process = null;

        try {
            process = Runtime.getRuntime().exec("su");
            PrintWriter = new PrintWriter(process.getOutputStream());
            PrintWriter.flush();
            PrintWriter.close();
            int value = process.waitFor();
            return returnResult(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (process != null) {
                process.destroy();
            }
        }
        return false;
    }

    private static boolean returnResult(int value) {
        // 代表成功
        if (value == 0) {
            return true;
        } else if (value == 1) { // 失败
            return false;
        } else { // 未知情况
            return false;
        }
    }
}
