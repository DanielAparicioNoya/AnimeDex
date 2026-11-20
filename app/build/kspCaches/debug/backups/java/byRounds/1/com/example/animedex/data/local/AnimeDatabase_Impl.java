package com.example.animedex.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.animedex.data.local.dao.AnimeDao;
import com.example.animedex.data.local.dao.AnimeDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AnimeDatabase_Impl extends AnimeDatabase {
  private volatile AnimeDao _animeDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `anime` (`anime_id` INTEGER NOT NULL, `title` TEXT NOT NULL, `score` REAL NOT NULL, `rank` INTEGER NOT NULL, `popularity` INTEGER NOT NULL, `members` INTEGER NOT NULL, `synopsis` TEXT, `start_date` TEXT, `end_date` TEXT, `type` TEXT NOT NULL, `episodes` INTEGER, `image_url` TEXT NOT NULL, PRIMARY KEY(`anime_id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_anime_rank` ON `anime` (`rank`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_anime_type` ON `anime` (`type`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `genre` (`genre_id` INTEGER NOT NULL, `name` TEXT NOT NULL, `category` TEXT NOT NULL, PRIMARY KEY(`genre_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `company` (`company_id` INTEGER NOT NULL, `name` TEXT, `company_type` TEXT NOT NULL, `image_url` TEXT, PRIMARY KEY(`company_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `character` (`character_id` INTEGER NOT NULL, `name` TEXT, `image_url` TEXT, PRIMARY KEY(`character_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `person` (`person_id` INTEGER NOT NULL, `name` TEXT, `person_type` TEXT NOT NULL, `image_url` TEXT, PRIMARY KEY(`person_id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_person_person_type` ON `person` (`person_type`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `anime_genre` (`anime_id` INTEGER NOT NULL, `genre_id` INTEGER NOT NULL, PRIMARY KEY(`anime_id`, `genre_id`), FOREIGN KEY(`anime_id`) REFERENCES `anime`(`anime_id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`genre_id`) REFERENCES `genre`(`genre_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_anime_genre_genre_id` ON `anime_genre` (`genre_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `anime_company` (`anime_id` INTEGER NOT NULL, `company_id` INTEGER NOT NULL, `role` TEXT NOT NULL, PRIMARY KEY(`anime_id`, `company_id`), FOREIGN KEY(`anime_id`) REFERENCES `anime`(`anime_id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`company_id`) REFERENCES `company`(`company_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_anime_company_company_id` ON `anime_company` (`company_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `anime_character` (`anime_id` INTEGER NOT NULL, `character_id` INTEGER NOT NULL, `role` TEXT NOT NULL, PRIMARY KEY(`anime_id`, `character_id`), FOREIGN KEY(`anime_id`) REFERENCES `anime`(`anime_id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`character_id`) REFERENCES `character`(`character_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_anime_character_character_id` ON `anime_character` (`character_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `anime_staff` (`anime_id` INTEGER NOT NULL, `person_id` INTEGER NOT NULL, `role` TEXT NOT NULL, PRIMARY KEY(`anime_id`, `person_id`), FOREIGN KEY(`anime_id`) REFERENCES `anime`(`anime_id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`person_id`) REFERENCES `person`(`person_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_anime_staff_person_id` ON `anime_staff` (`person_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `character_voice_actor` (`character_id` INTEGER NOT NULL, `person_id` INTEGER NOT NULL, `language` TEXT NOT NULL, PRIMARY KEY(`character_id`, `person_id`, `language`), FOREIGN KEY(`character_id`) REFERENCES `character`(`character_id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`person_id`) REFERENCES `person`(`person_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_character_voice_actor_person_id` ON `character_voice_actor` (`person_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd94f4c9becdde785855110a278942a8a')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `anime`");
        db.execSQL("DROP TABLE IF EXISTS `genre`");
        db.execSQL("DROP TABLE IF EXISTS `company`");
        db.execSQL("DROP TABLE IF EXISTS `character`");
        db.execSQL("DROP TABLE IF EXISTS `person`");
        db.execSQL("DROP TABLE IF EXISTS `anime_genre`");
        db.execSQL("DROP TABLE IF EXISTS `anime_company`");
        db.execSQL("DROP TABLE IF EXISTS `anime_character`");
        db.execSQL("DROP TABLE IF EXISTS `anime_staff`");
        db.execSQL("DROP TABLE IF EXISTS `character_voice_actor`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsAnime = new HashMap<String, TableInfo.Column>(12);
        _columnsAnime.put("anime_id", new TableInfo.Column("anime_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("score", new TableInfo.Column("score", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("rank", new TableInfo.Column("rank", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("popularity", new TableInfo.Column("popularity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("members", new TableInfo.Column("members", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("synopsis", new TableInfo.Column("synopsis", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("start_date", new TableInfo.Column("start_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("end_date", new TableInfo.Column("end_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("episodes", new TableInfo.Column("episodes", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("image_url", new TableInfo.Column("image_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAnime = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAnime = new HashSet<TableInfo.Index>(2);
        _indicesAnime.add(new TableInfo.Index("index_anime_rank", false, Arrays.asList("rank"), Arrays.asList("ASC")));
        _indicesAnime.add(new TableInfo.Index("index_anime_type", false, Arrays.asList("type"), Arrays.asList("ASC")));
        final TableInfo _infoAnime = new TableInfo("anime", _columnsAnime, _foreignKeysAnime, _indicesAnime);
        final TableInfo _existingAnime = TableInfo.read(db, "anime");
        if (!_infoAnime.equals(_existingAnime)) {
          return new RoomOpenHelper.ValidationResult(false, "anime(com.example.animedex.data.local.entity.AnimeEntity).\n"
                  + " Expected:\n" + _infoAnime + "\n"
                  + " Found:\n" + _existingAnime);
        }
        final HashMap<String, TableInfo.Column> _columnsGenre = new HashMap<String, TableInfo.Column>(3);
        _columnsGenre.put("genre_id", new TableInfo.Column("genre_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGenre.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGenre.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGenre = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGenre = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGenre = new TableInfo("genre", _columnsGenre, _foreignKeysGenre, _indicesGenre);
        final TableInfo _existingGenre = TableInfo.read(db, "genre");
        if (!_infoGenre.equals(_existingGenre)) {
          return new RoomOpenHelper.ValidationResult(false, "genre(com.example.animedex.data.local.entity.GenreEntity).\n"
                  + " Expected:\n" + _infoGenre + "\n"
                  + " Found:\n" + _existingGenre);
        }
        final HashMap<String, TableInfo.Column> _columnsCompany = new HashMap<String, TableInfo.Column>(4);
        _columnsCompany.put("company_id", new TableInfo.Column("company_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompany.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompany.put("company_type", new TableInfo.Column("company_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompany.put("image_url", new TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCompany = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCompany = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCompany = new TableInfo("company", _columnsCompany, _foreignKeysCompany, _indicesCompany);
        final TableInfo _existingCompany = TableInfo.read(db, "company");
        if (!_infoCompany.equals(_existingCompany)) {
          return new RoomOpenHelper.ValidationResult(false, "company(com.example.animedex.data.local.entity.CompanyEntity).\n"
                  + " Expected:\n" + _infoCompany + "\n"
                  + " Found:\n" + _existingCompany);
        }
        final HashMap<String, TableInfo.Column> _columnsCharacter = new HashMap<String, TableInfo.Column>(3);
        _columnsCharacter.put("character_id", new TableInfo.Column("character_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacter.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacter.put("image_url", new TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCharacter = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCharacter = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCharacter = new TableInfo("character", _columnsCharacter, _foreignKeysCharacter, _indicesCharacter);
        final TableInfo _existingCharacter = TableInfo.read(db, "character");
        if (!_infoCharacter.equals(_existingCharacter)) {
          return new RoomOpenHelper.ValidationResult(false, "character(com.example.animedex.data.local.entity.CharacterEntity).\n"
                  + " Expected:\n" + _infoCharacter + "\n"
                  + " Found:\n" + _existingCharacter);
        }
        final HashMap<String, TableInfo.Column> _columnsPerson = new HashMap<String, TableInfo.Column>(4);
        _columnsPerson.put("person_id", new TableInfo.Column("person_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPerson.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPerson.put("person_type", new TableInfo.Column("person_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPerson.put("image_url", new TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPerson = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPerson = new HashSet<TableInfo.Index>(1);
        _indicesPerson.add(new TableInfo.Index("index_person_person_type", false, Arrays.asList("person_type"), Arrays.asList("ASC")));
        final TableInfo _infoPerson = new TableInfo("person", _columnsPerson, _foreignKeysPerson, _indicesPerson);
        final TableInfo _existingPerson = TableInfo.read(db, "person");
        if (!_infoPerson.equals(_existingPerson)) {
          return new RoomOpenHelper.ValidationResult(false, "person(com.example.animedex.data.local.entity.PersonEntity).\n"
                  + " Expected:\n" + _infoPerson + "\n"
                  + " Found:\n" + _existingPerson);
        }
        final HashMap<String, TableInfo.Column> _columnsAnimeGenre = new HashMap<String, TableInfo.Column>(2);
        _columnsAnimeGenre.put("anime_id", new TableInfo.Column("anime_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeGenre.put("genre_id", new TableInfo.Column("genre_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAnimeGenre = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysAnimeGenre.add(new TableInfo.ForeignKey("anime", "CASCADE", "NO ACTION", Arrays.asList("anime_id"), Arrays.asList("anime_id")));
        _foreignKeysAnimeGenre.add(new TableInfo.ForeignKey("genre", "CASCADE", "NO ACTION", Arrays.asList("genre_id"), Arrays.asList("genre_id")));
        final HashSet<TableInfo.Index> _indicesAnimeGenre = new HashSet<TableInfo.Index>(1);
        _indicesAnimeGenre.add(new TableInfo.Index("index_anime_genre_genre_id", false, Arrays.asList("genre_id"), Arrays.asList("ASC")));
        final TableInfo _infoAnimeGenre = new TableInfo("anime_genre", _columnsAnimeGenre, _foreignKeysAnimeGenre, _indicesAnimeGenre);
        final TableInfo _existingAnimeGenre = TableInfo.read(db, "anime_genre");
        if (!_infoAnimeGenre.equals(_existingAnimeGenre)) {
          return new RoomOpenHelper.ValidationResult(false, "anime_genre(com.example.animedex.data.local.entity.AnimeGenreCrossRef).\n"
                  + " Expected:\n" + _infoAnimeGenre + "\n"
                  + " Found:\n" + _existingAnimeGenre);
        }
        final HashMap<String, TableInfo.Column> _columnsAnimeCompany = new HashMap<String, TableInfo.Column>(3);
        _columnsAnimeCompany.put("anime_id", new TableInfo.Column("anime_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeCompany.put("company_id", new TableInfo.Column("company_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeCompany.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAnimeCompany = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysAnimeCompany.add(new TableInfo.ForeignKey("anime", "CASCADE", "NO ACTION", Arrays.asList("anime_id"), Arrays.asList("anime_id")));
        _foreignKeysAnimeCompany.add(new TableInfo.ForeignKey("company", "CASCADE", "NO ACTION", Arrays.asList("company_id"), Arrays.asList("company_id")));
        final HashSet<TableInfo.Index> _indicesAnimeCompany = new HashSet<TableInfo.Index>(1);
        _indicesAnimeCompany.add(new TableInfo.Index("index_anime_company_company_id", false, Arrays.asList("company_id"), Arrays.asList("ASC")));
        final TableInfo _infoAnimeCompany = new TableInfo("anime_company", _columnsAnimeCompany, _foreignKeysAnimeCompany, _indicesAnimeCompany);
        final TableInfo _existingAnimeCompany = TableInfo.read(db, "anime_company");
        if (!_infoAnimeCompany.equals(_existingAnimeCompany)) {
          return new RoomOpenHelper.ValidationResult(false, "anime_company(com.example.animedex.data.local.entity.AnimeCompany).\n"
                  + " Expected:\n" + _infoAnimeCompany + "\n"
                  + " Found:\n" + _existingAnimeCompany);
        }
        final HashMap<String, TableInfo.Column> _columnsAnimeCharacter = new HashMap<String, TableInfo.Column>(3);
        _columnsAnimeCharacter.put("anime_id", new TableInfo.Column("anime_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeCharacter.put("character_id", new TableInfo.Column("character_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeCharacter.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAnimeCharacter = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysAnimeCharacter.add(new TableInfo.ForeignKey("anime", "CASCADE", "NO ACTION", Arrays.asList("anime_id"), Arrays.asList("anime_id")));
        _foreignKeysAnimeCharacter.add(new TableInfo.ForeignKey("character", "CASCADE", "NO ACTION", Arrays.asList("character_id"), Arrays.asList("character_id")));
        final HashSet<TableInfo.Index> _indicesAnimeCharacter = new HashSet<TableInfo.Index>(1);
        _indicesAnimeCharacter.add(new TableInfo.Index("index_anime_character_character_id", false, Arrays.asList("character_id"), Arrays.asList("ASC")));
        final TableInfo _infoAnimeCharacter = new TableInfo("anime_character", _columnsAnimeCharacter, _foreignKeysAnimeCharacter, _indicesAnimeCharacter);
        final TableInfo _existingAnimeCharacter = TableInfo.read(db, "anime_character");
        if (!_infoAnimeCharacter.equals(_existingAnimeCharacter)) {
          return new RoomOpenHelper.ValidationResult(false, "anime_character(com.example.animedex.data.local.entity.AnimeCharacter).\n"
                  + " Expected:\n" + _infoAnimeCharacter + "\n"
                  + " Found:\n" + _existingAnimeCharacter);
        }
        final HashMap<String, TableInfo.Column> _columnsAnimeStaff = new HashMap<String, TableInfo.Column>(3);
        _columnsAnimeStaff.put("anime_id", new TableInfo.Column("anime_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeStaff.put("person_id", new TableInfo.Column("person_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeStaff.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAnimeStaff = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysAnimeStaff.add(new TableInfo.ForeignKey("anime", "CASCADE", "NO ACTION", Arrays.asList("anime_id"), Arrays.asList("anime_id")));
        _foreignKeysAnimeStaff.add(new TableInfo.ForeignKey("person", "CASCADE", "NO ACTION", Arrays.asList("person_id"), Arrays.asList("person_id")));
        final HashSet<TableInfo.Index> _indicesAnimeStaff = new HashSet<TableInfo.Index>(1);
        _indicesAnimeStaff.add(new TableInfo.Index("index_anime_staff_person_id", false, Arrays.asList("person_id"), Arrays.asList("ASC")));
        final TableInfo _infoAnimeStaff = new TableInfo("anime_staff", _columnsAnimeStaff, _foreignKeysAnimeStaff, _indicesAnimeStaff);
        final TableInfo _existingAnimeStaff = TableInfo.read(db, "anime_staff");
        if (!_infoAnimeStaff.equals(_existingAnimeStaff)) {
          return new RoomOpenHelper.ValidationResult(false, "anime_staff(com.example.animedex.data.local.entity.AnimeStaff).\n"
                  + " Expected:\n" + _infoAnimeStaff + "\n"
                  + " Found:\n" + _existingAnimeStaff);
        }
        final HashMap<String, TableInfo.Column> _columnsCharacterVoiceActor = new HashMap<String, TableInfo.Column>(3);
        _columnsCharacterVoiceActor.put("character_id", new TableInfo.Column("character_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterVoiceActor.put("person_id", new TableInfo.Column("person_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCharacterVoiceActor.put("language", new TableInfo.Column("language", "TEXT", true, 3, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCharacterVoiceActor = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysCharacterVoiceActor.add(new TableInfo.ForeignKey("character", "CASCADE", "NO ACTION", Arrays.asList("character_id"), Arrays.asList("character_id")));
        _foreignKeysCharacterVoiceActor.add(new TableInfo.ForeignKey("person", "CASCADE", "NO ACTION", Arrays.asList("person_id"), Arrays.asList("person_id")));
        final HashSet<TableInfo.Index> _indicesCharacterVoiceActor = new HashSet<TableInfo.Index>(1);
        _indicesCharacterVoiceActor.add(new TableInfo.Index("index_character_voice_actor_person_id", false, Arrays.asList("person_id"), Arrays.asList("ASC")));
        final TableInfo _infoCharacterVoiceActor = new TableInfo("character_voice_actor", _columnsCharacterVoiceActor, _foreignKeysCharacterVoiceActor, _indicesCharacterVoiceActor);
        final TableInfo _existingCharacterVoiceActor = TableInfo.read(db, "character_voice_actor");
        if (!_infoCharacterVoiceActor.equals(_existingCharacterVoiceActor)) {
          return new RoomOpenHelper.ValidationResult(false, "character_voice_actor(com.example.animedex.data.local.entity.CharacterVoiceActor).\n"
                  + " Expected:\n" + _infoCharacterVoiceActor + "\n"
                  + " Found:\n" + _existingCharacterVoiceActor);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d94f4c9becdde785855110a278942a8a", "7cb69cbdce7aefc5d516ae0bee404804");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "anime","genre","company","character","person","anime_genre","anime_company","anime_character","anime_staff","character_voice_actor");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `anime`");
      _db.execSQL("DELETE FROM `genre`");
      _db.execSQL("DELETE FROM `company`");
      _db.execSQL("DELETE FROM `character`");
      _db.execSQL("DELETE FROM `person`");
      _db.execSQL("DELETE FROM `anime_genre`");
      _db.execSQL("DELETE FROM `anime_company`");
      _db.execSQL("DELETE FROM `anime_character`");
      _db.execSQL("DELETE FROM `anime_staff`");
      _db.execSQL("DELETE FROM `character_voice_actor`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AnimeDao.class, AnimeDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AnimeDao animeDao() {
    if (_animeDao != null) {
      return _animeDao;
    } else {
      synchronized(this) {
        if(_animeDao == null) {
          _animeDao = new AnimeDao_Impl(this);
        }
        return _animeDao;
      }
    }
  }
}
