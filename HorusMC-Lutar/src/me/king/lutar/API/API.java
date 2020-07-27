/*    */ package me.king.lutar.API;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;

/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class API
/*    */ {
/*    */   public static boolean Check(String Local)
/*    */   {
/* 17 */     File arquivo = new File("plugins/HorusLutar/Locais/" + Local + ".yml");
/* 18 */     FileConfiguration local = YamlConfiguration.loadConfiguration(arquivo);
/*    */     
/* 20 */     if (local.getString(Local + ".Mundo") != null) {
/* 21 */       return true;
/*    */     }
/* 23 */     return false;
/*    */   }
/*    */   
/*    */   public static void Delete(String Local)
/*    */   {
/* 28 */     File arquivo = new File("plugins/HorusLutar/Locais/" + Local + ".yml");
/*    */     
/* 30 */     arquivo.delete();
/*    */   }
/*    */   
/*    */ 
/*    */   public static void Teleporte(Player jogador, String Local)
/*    */   {
/* 36 */     File arquivo = new File("plugins/HorusLutar/Locais/" + Local + ".yml");
/* 37 */     FileConfiguration local = YamlConfiguration.loadConfiguration(arquivo);
/*    */     
/* 39 */     double x = local.getDouble(String.valueOf(String.valueOf(Local)) + ".X");
/* 40 */     double y = local.getDouble(String.valueOf(String.valueOf(Local)) + ".Y");
/* 41 */     double z = local.getDouble(String.valueOf(String.valueOf(Local)) + ".Z");
/* 42 */     float yaw = (float)local.getDouble(String.valueOf(String.valueOf(Local)) + ".Yaw");
/* 43 */     float pitch = (float)local.getDouble(String.valueOf(String.valueOf(Local)) + ".Pitch");
/* 44 */     World mundo = Bukkit.getServer().getWorld(local.getString(String.valueOf(String.valueOf(Local)) + ".Mundo"));
/* 45 */     Location loc = new Location(mundo, x, y, z);
/* 46 */     loc.setYaw(yaw);
/* 47 */     loc.setPitch(pitch);
/* 48 */     jogador.teleport(loc);
/*    */   }
/*    */   
/*    */   public static void SetLocal(Player jogador, String Local) {
/* 52 */     Location loc = jogador.getLocation();
/* 53 */     File arquivo = new File("plugins/HorusLutar/Locais/" + Local + ".yml");
/* 54 */     FileConfiguration local = YamlConfiguration.loadConfiguration(arquivo);
/* 55 */     double x = loc.getX();
/* 56 */     double y = loc.getY();
/* 57 */     double z = loc.getZ();
/* 58 */     float yaw = loc.getYaw();
/* 59 */     float pitch = loc.getPitch();
/* 60 */     String mundo = loc.getWorld().getName();
/* 61 */     local.set(String.valueOf(String.valueOf(Local)) + ".X", Double.valueOf(x));
/* 62 */     local.set(String.valueOf(String.valueOf(Local)) + ".Y", Double.valueOf(y));
/* 63 */     local.set(String.valueOf(String.valueOf(Local)) + ".Z", Double.valueOf(z));
/* 64 */     local.set(String.valueOf(String.valueOf(Local)) + ".Yaw", Float.valueOf(yaw));
/* 65 */     local.set(String.valueOf(String.valueOf(Local)) + ".Pitch", Float.valueOf(pitch));
/* 66 */     local.set(String.valueOf(String.valueOf(Local)) + ".Mundo", mundo);
/*    */     try {
/* 68 */       local.save(arquivo);
/*    */     } catch (IOException e) {
/* 70 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

