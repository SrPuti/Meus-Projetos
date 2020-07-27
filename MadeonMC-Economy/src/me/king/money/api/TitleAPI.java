/*     */ package me.king.money.api;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;

/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ public class TitleAPI
/*     */ {
/*     */   public static void setTible(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
/*     */   {
/*  14 */     sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
/*     */   }
/*     */   
/*     */   public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message)
/*     */   {
/*  19 */     sendTitle(player, fadeIn, stay, fadeOut, null, message);
/*     */   }
/*     */   
/*     */   public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
/*     */   {
/*  24 */     sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
/*     */   }
/*     */   
/*     */   public static void sendPacket(Player player, Object packet) {
/*     */     try {
/*  29 */       Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, 
/*  30 */         new Object[0]);
/*  31 */       Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
/*  32 */       playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, new Object[] { packet });
/*     */     } catch (Exception e) {
/*  34 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Class<?> getNMSClass(String name) {
/*  39 */     String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
/*     */     try {
/*  41 */       return Class.forName("net.minecraft.server." + version + "." + name);
/*     */     } catch (ClassNotFoundException e) {
/*  43 */       e.printStackTrace(); }
/*  44 */     return null;
/*     */   }
/*     */   
/*     */   public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
/*     */   {
/*     */     try
/*     */     {
/*  51 */       if (title != null) {
/*  52 */         title = ChatColor.translateAlternateColorCodes('&', title);
/*  53 */         title = title.replaceAll("%player%", player.getDisplayName());
/*  54 */         Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE")
/*  55 */           .get(null);
/*  56 */         Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
/*  57 */           .getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
/*  58 */         Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] {
/*  59 */           getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), 
/*  60 */           Integer.TYPE, Integer.TYPE, Integer.TYPE });
/*  61 */         Object titlePacket = titleConstructor.newInstance(new Object[] { enumTitle, chatTitle, fadeIn, stay, fadeOut });
/*  62 */         sendPacket(player, titlePacket);
/*     */       }
/*  64 */       if (subtitle != null) {
/*  65 */         subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
/*  66 */         subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
/*  67 */         Object enumSubtitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0]
/*  68 */           .getField("SUBTITLE").get(null);
/*  69 */         Object chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
/*  70 */           .getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
/*  71 */         Constructor<?> subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] {
/*  72 */           getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), 
/*  73 */           Integer.TYPE, Integer.TYPE, Integer.TYPE });
/*  74 */         Object subtitlePacket = subtitleConstructor.newInstance(new Object[] { enumSubtitle, chatSubtitle, fadeIn, stay, 
/*  75 */           fadeOut });
/*  76 */         sendPacket(player, subtitlePacket);
/*     */       }
/*     */     } catch (Exception e) {
/*  79 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void sendTabTitle(Player player, String header, String footer) {
/*  84 */     if (header == null) {
/*  85 */       header = "";
/*     */     }
/*  87 */     header = ChatColor.translateAlternateColorCodes('&', header);
/*  88 */     if (footer == null) {
/*  89 */       footer = "";
/*     */     }
/*  91 */     footer = ChatColor.translateAlternateColorCodes('&', footer);
/*  92 */     header = header.replaceAll("%player%", player.getDisplayName());
/*  93 */     footer = footer.replaceAll("%player%", player.getDisplayName());
/*     */     try {
/*  95 */       Object tabHeader = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
/*  96 */         .getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + header + "\"}" });
/*  97 */       Object tabFooter = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
/*  98 */         .getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + footer + "\"}" });
/*  99 */       Constructor<?> titleConstructor = getNMSClass("PacketPlayOutPlayerListHeaderFooter")
/* 100 */         .getConstructor(new Class[] {getNMSClass("IChatBaseComponent") });
/* 101 */       Object packet = titleConstructor.newInstance(new Object[] { tabHeader });
/* 102 */       Field field = packet.getClass().getDeclaredField("b");
/* 103 */       field.setAccessible(true);
/* 104 */       field.set(packet, tabFooter);
/* 105 */       sendPacket(player, packet);
/*     */     } catch (Exception ex) {
/* 107 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

