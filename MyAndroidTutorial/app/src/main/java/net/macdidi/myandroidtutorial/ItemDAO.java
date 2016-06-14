package net.macdidi.myandroidtutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 資料功能類別
public class ItemDAO {
    // 表格名稱
    public static final String TABLE_NAME = "item";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    public static final String DATETIME_COLUMN = "datetime";
    public static final String COLOR_COLUMN = "color";
    public static final String TITLE_COLUMN = "title";
    public static final String CONTENT_COLUMN = "content";
    public static final String FILENAME_COLUMN = "filename";
    public static final String RECFILENAME_COLUMN = "recfilename";
    public static final String LATITUDE_COLUMN = "latitude";
    public static final String LONGITUDE_COLUMN = "longitude";
    public static final String LASTMODIFY_COLUMN = "lastmodify";
    public static final String COST_COLUMN = "cost";
    public static final String TYPE_COLUMN = "type";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATETIME_COLUMN + " INTEGER NOT NULL, " +
                    COLOR_COLUMN + " INTEGER NOT NULL, " +
                    TITLE_COLUMN + " TEXT NOT NULL, " +
                    CONTENT_COLUMN + " TEXT NOT NULL, " +
                    FILENAME_COLUMN + " TEXT, " +
                    RECFILENAME_COLUMN + " TEXT, " +
                    LATITUDE_COLUMN + " REAL, " +
                    LONGITUDE_COLUMN + " REAL, " +
                    LASTMODIFY_COLUMN + " INTEGER, " +
                    COST_COLUMN + " INTEGER NOT NULL, " +
                    TYPE_COLUMN + " INTEGER NOT NULL)";

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public ItemDAO(Context context) {
        db = MyDBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    // 新增參數指定的物件
    public Item insert(Item item) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(DATETIME_COLUMN, item.getDatetime());
        cv.put(COLOR_COLUMN, item.getColor().parseColor());
        cv.put(TITLE_COLUMN, item.getTitle());
        cv.put(CONTENT_COLUMN, item.getContent());
        cv.put(FILENAME_COLUMN, item.getFileName());
        cv.put(RECFILENAME_COLUMN, item.getRecFileName());
        cv.put(LATITUDE_COLUMN, item.getLatitude());
        cv.put(LONGITUDE_COLUMN, item.getLongitude());
        cv.put(LASTMODIFY_COLUMN, item.getLastModify());
        cv.put(COST_COLUMN, item.getCost());
        cv.put(TYPE_COLUMN, item.getType());

        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
        item.setId(id);
        // 回傳結果
        return item;
    }

    // 修改參數指定的物件
    public boolean update(Item item) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(DATETIME_COLUMN, item.getDatetime());
        cv.put(COLOR_COLUMN, item.getColor().parseColor());
        cv.put(TITLE_COLUMN, item.getTitle());
        cv.put(CONTENT_COLUMN, item.getContent());
        cv.put(FILENAME_COLUMN, item.getFileName());
        cv.put(RECFILENAME_COLUMN, item.getRecFileName());
        cv.put(LATITUDE_COLUMN, item.getLatitude());
        cv.put(LONGITUDE_COLUMN, item.getLongitude());
        cv.put(LASTMODIFY_COLUMN, item.getLastModify());
        cv.put(COST_COLUMN, item.getCost());
        cv.put(TYPE_COLUMN, item.getType());

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.getId();

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    // 刪除參數指定編號的資料
    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }

    // 讀取所有記事資料
    public List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public Item get(long id) {
        // 準備回傳結果用的物件
        Item item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    // 把Cursor目前的資料包裝為物件
    public Item getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Item result = new Item();

        result.setId(cursor.getLong(0));
        result.setDatetime(cursor.getLong(1));
        result.setColor(ItemActivity.getColors(cursor.getInt(2)));
        result.setTitle(cursor.getString(3));
        result.setContent(cursor.getString(4));
        result.setFileName(cursor.getString(5));
        result.setRecFileName(cursor.getString(6));
        result.setLatitude(cursor.getDouble(7));
        result.setLongitude(cursor.getDouble(8));
        result.setLastModify(cursor.getLong(9));
        result.setCost(cursor.getLong(10));
        result.setType(cursor.getLong(11));

        // 回傳結果
        return result;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    // 建立範例資料
    public void sample() {
        Item item = new Item(0, new Date().getTime(), Colors.RED, "關於Android Tutorial的事情.", "Hello content", "", "", 0, 0, 0,10,0);
        Item item2 = new Item(0, new Date().getTime(), Colors.BLUE, "一隻非常可愛的小狗狗!", "她的名字叫「大熱狗」，又叫\n作「奶嘴」，是一隻非常可愛\n的小狗。", "", "", 25.04719, 121.516981, 0,20,0);
        Item item3 = new Item(0, new Date().getTime(), Colors.GREEN, "一首非常好聽的音樂！", "Hello content", "", "", 0, 0, 0,30,0);
        Item item4 = new Item(0, new Date().getTime(), Colors.ORANGE, "儲存在資料庫的資料", "Hello content", "", "", 0, 0, 0,40,0);
        Item item5 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "鉛筆", "鉛筆一隻", "", "", 0, 0, 0,50,4);
        Item item6 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "畫筆", "畫筆一組", "", "", 0, 0, 0,500,4);
        Item item7 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "鋼筆", "萬寶龍鋼筆", "", "", 0, 0, 0,50000,4);
        Item item8 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "毛筆", "大楷小楷毛筆", "", "", 0, 0, 0,700,4);
        Item item9 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "彩色筆", "彩色筆一盒", "", "", 0, 0, 0,300,4);
        Item item10 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "襯衫", "ZARA白色襯衫", "", "", 0, 0, 0,1000,0);
        Item item11 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "牛仔褲", "uniqlo淡藍色牛仔褲", "", "", 0, 0, 0,1500,0);
        Item item12 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "外套", "gap 灰色外套", "", "", 0, 0, 0,3500,0);
        Item item13 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "風衣", "PRADA米色風衣", "", "", 0, 0, 0,50000,0);
        Item item14 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "西裝外套", "H&M 西裝外套 ", "", "", 0, 0, 0,4500,0);
        Item item15 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "iMac", " APPLE 27' iMac ", "", "", 0, 0, 0,75000,2);
        Item item16 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "iPhone", " iPhone 6s plus ", "", "", 0, 0, 0,31000,2);
        Item item17 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "iPod", " iPod 4th ", "", "", 0, 0, 0,5200,2);
        Item item18 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "Mac Book", " APPLE 15' MacBookPro  ", "", "", 0, 0, 0,63000,2);
        Item item19 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "iPad", " iPad Pro 2 ", "", "", 0, 0, 0,28000,2);
        Item item20 = new Item(0, new Date().getTime(), Colors.LIGHTGREY, "iPad Pro", " APPLE iPad Pro 12' ", "", "", 0, 0, 0,33000,2);



        insert(item);
        insert(item2);
        insert(item3);
        insert(item4);
        insert(item5);
        insert(item6);
        insert(item7);
        insert(item8);
        insert(item9);
        insert(item10);
        insert(item11);
        insert(item12);
        insert(item13);
        insert(item14);
        insert(item15);
        insert(item16);
        insert(item17);
        insert(item18);
        insert(item19);
        insert(item20);
    }

}