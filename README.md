# MyAndroidTutorial

![Android](https://img.shields.io/badge/Android-23-green.svg)
![Java](https://img.shields.io/badge/Java-8-orange.svg)
![Version](https://img.shields.io/badge/Version-1.0-blue.svg)

An Android tutorial application that demonstrates various Android APIs and features through a practical memo/note-taking app inspired by accounting software.

一個Android教學應用程式，通過實用的備忘錄/記事本應用程式（靈感來自記帳軟體）展示各種Android API和功能。

## 📱 Features | 功能特色

### 📝 Core Functionality | 核心功能
- ✅ **Item Management** | **項目管理** - Create, edit, delete memo items with categories and costs
- 🏷️ **Categorization** | **分類系統** - Organize items by different categories
- 💰 **Cost Tracking** | **費用追蹤** - Add and track costs for each item
- 🎨 **Color Themes** | **顏色主題** - Customize item colors with built-in color picker
- 🗃️ **Data Persistence** | **數據持久化** - SQLite database for reliable data storage

### 📸 Media Features | 媒體功能
- 📷 **Photo Attachment** | **照片附件** - Take and attach photos to memo items
- 🎤 **Audio Recording** | **音頻錄製** - Record voice memos with playback functionality
- 🎵 **Audio Playback** | **音頻播放** - Play recorded voice memos

### 🌍 Location & Device | 位置與設備
- 🗺️ **Google Maps Integration** | **Google地圖集成** - View and save locations
- 📱 **Device Information** | **設備信息** - Display comprehensive device details
- ⚙️ **Settings & Preferences** | **設置與偏好** - Customizable app preferences

## 🛠️ Technical Specifications | 技術規格

### Platform Requirements | 平台要求
- **Minimum SDK:** Android 15 (API Level 15)
- **Target SDK:** Android 23 (API Level 23)
- **Build Tools:** 23.0.1
- **Gradle:** 2.2.0

### Dependencies | 依賴項
- Android Support Library v7: 23.4.0
- Google Play Services: 9.6.1
- JUnit: 4.12 (testing)

### Permissions | 權限
- `WRITE_EXTERNAL_STORAGE` - For saving photos and audio files
- `RECORD_AUDIO` - For voice memo recording
- `ACCESS_FINE_LOCATION` - For location features
- `android.hardware.camera` - For photo capture functionality

## 🏗️ Architecture | 架構

### Key Components | 核心組件

#### Activities | 活動
- **MainActivity** - Main item list and navigation
- **ItemActivity** - Create and edit individual items
- **MapsActivity** - Google Maps integration
- **RecordActivity** - Audio recording interface
- **PlayActivity** - Audio playback interface
- **ColorActivity** - Color selection dialog
- **DeviceinfoActivity** - Device information display
- **AboutActivity** - Application information
- **PrefActivity** - Settings and preferences

#### Data Layer | 數據層
- **Item.java** - Core data model with serialization support
- **ItemDAO.java** - Data Access Object for database operations
- **MyDBHelper.java** - SQLite database helper
- **ItemAdapter.java** - ListView adapter for item display

#### Utilities | 工具類
- **FileUtil.java** - File operations and external storage management
- **Colors.java** - Color theme enumeration

## 🚀 Installation | 安裝

### Prerequisites | 前置要求
- Android Studio 2.0+
- Android SDK 23
- Google Play Services
- Device with API Level 15+

### Setup Steps | 設置步驟

1. **Clone the repository | 克隆儲存庫**
   ```bash
   git clone https://github.com/HyperLee/MyAndroidTutorial.git
   cd MyAndroidTutorial
   ```

2. **Open in Android Studio | 在Android Studio中打開**
   - Open Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the cloned directory and select it

3. **Configure Google Maps API | 配置Google地圖API**
   - Get a Google Maps API key from [Google Cloud Console](https://console.cloud.google.com/)
   - Add your API key to `app/src/debug/res/values/google_maps_api.xml`
   - Add your release API key to `app/src/release/res/values/google_maps_api.xml`

4. **Build and Run | 構建並運行**
   ```bash
   ./gradlew build
   ./gradlew installDebug
   ```

## 📖 Usage Guide | 使用指南

### Creating Items | 創建項目
1. Tap the "+" button on the main screen
2. Fill in the title, content, and cost
3. Select a category from the dropdown
4. Choose a color theme (optional)
5. Add photo or voice memo (optional)
6. Save the item

### Managing Items | 管理項目
- **Edit:** Tap on any item to modify it
- **Delete:** Long-press and select delete option
- **Search:** Use the search functionality to find specific items

### Media Features | 媒體功能
- **Photos:** Tap the camera icon to take and attach photos
- **Voice Memos:** Use the microphone icon to record audio notes
- **Location:** Add location information using the map feature

## 🧪 Testing | 測試

Run unit tests:
```bash
./gradlew test
```

Run instrumentation tests:
```bash
./gradlew connectedAndroidTest
```

## 📁 Project Structure | 項目結構

```
MyAndroidTutorial/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/net/macdidi/myandroidtutorial/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── ItemActivity.java
│   │   │   │   ├── Item.java
│   │   │   │   ├── ItemDAO.java
│   │   │   │   ├── MyDBHelper.java
│   │   │   │   ├── FileUtil.java
│   │   │   │   ├── Colors.java
│   │   │   │   └── ... (other activities)
│   │   │   ├── res/
│   │   │   │   ├── layout/ (UI layouts)
│   │   │   │   ├── values/ (strings, colors, etc.)
│   │   │   │   └── drawable/ (icons and images)
│   │   │   └── AndroidManifest.xml
│   │   ├── test/ (unit tests)
│   │   └── androidTest/ (instrumentation tests)
│   └── build.gradle
├── build.gradle
└── README.md
```

## 🤝 Contributing | 貢獻

We welcome contributions to improve this Android tutorial project!

### How to Contribute | 如何貢獻
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Coding Standards | 編碼標準
- Follow Android development best practices
- Use meaningful variable and method names
- Add comments for complex logic
- Include unit tests for new features

## 📝 License | 許可證

This project is for educational and tutorial purposes. Please refer to the original author's license terms.

該項目僅供教育和教學用途。請參考原作者的許可條款。

## 👥 Credits | 致謝

- **Original Author:** 基於他人的程式碼繼續開發
- **Modified by:** HyperLee
- **Tutorial Purpose:** Android API practice and learning

## 📞 Support | 支持

For questions and support:
- Create an [Issue](https://github.com/HyperLee/MyAndroidTutorial/issues)
- Check existing documentation
- Review the code comments

## 🔄 Version History | 版本歷史

- **v1.0** - Initial release with core functionality
  - Basic memo management
  - Photo and audio features
  - Google Maps integration
  - SQLite database implementation

---

**Note:** This is a tutorial/learning project designed to demonstrate various Android development concepts and APIs.

**注意：** 這是一個教學/學習項目，旨在演示各種Android開發概念和API。
