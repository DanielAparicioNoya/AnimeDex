package com.example.animedex.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import com.example.animedex.data.local.entity.AnimeEntity;
import com.example.animedex.data.local.entity.GenreEntity;
import com.example.animedex.data.local.relation.AnimeWithGenres;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AnimeDao_Impl implements AnimeDao {
  private final RoomDatabase __db;

  public AnimeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Flow<List<AnimeWithGenres>> getAllAnimes() {
    final String _sql = "SELECT * FROM anime";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"anime_genre", "genre",
        "anime"}, new Callable<List<AnimeWithGenres>>() {
      @Override
      @NonNull
      public List<AnimeWithGenres> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "anime_id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
            final int _cursorIndexOfRank = CursorUtil.getColumnIndexOrThrow(_cursor, "rank");
            final int _cursorIndexOfPopularity = CursorUtil.getColumnIndexOrThrow(_cursor, "popularity");
            final int _cursorIndexOfMembers = CursorUtil.getColumnIndexOrThrow(_cursor, "members");
            final int _cursorIndexOfSynopsis = CursorUtil.getColumnIndexOrThrow(_cursor, "synopsis");
            final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
            final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
            final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
            final int _cursorIndexOfEpisodes = CursorUtil.getColumnIndexOrThrow(_cursor, "episodes");
            final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
            final LongSparseArray<ArrayList<GenreEntity>> _collectionGenres = new LongSparseArray<ArrayList<GenreEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfAnimeId);
              if (!_collectionGenres.containsKey(_tmpKey)) {
                _collectionGenres.put(_tmpKey, new ArrayList<GenreEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipgenreAscomExampleAnimedexDataLocalEntityGenreEntity(_collectionGenres);
            final List<AnimeWithGenres> _result = new ArrayList<AnimeWithGenres>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final AnimeWithGenres _item;
              final AnimeEntity _tmpAnime;
              final int _tmpAnimeId;
              _tmpAnimeId = _cursor.getInt(_cursorIndexOfAnimeId);
              final String _tmpTitle;
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              final double _tmpScore;
              _tmpScore = _cursor.getDouble(_cursorIndexOfScore);
              final int _tmpRank;
              _tmpRank = _cursor.getInt(_cursorIndexOfRank);
              final int _tmpPopularity;
              _tmpPopularity = _cursor.getInt(_cursorIndexOfPopularity);
              final int _tmpMembers;
              _tmpMembers = _cursor.getInt(_cursorIndexOfMembers);
              final String _tmpSynopsis;
              if (_cursor.isNull(_cursorIndexOfSynopsis)) {
                _tmpSynopsis = null;
              } else {
                _tmpSynopsis = _cursor.getString(_cursorIndexOfSynopsis);
              }
              final String _tmpStartDate;
              if (_cursor.isNull(_cursorIndexOfStartDate)) {
                _tmpStartDate = null;
              } else {
                _tmpStartDate = _cursor.getString(_cursorIndexOfStartDate);
              }
              final String _tmpEndDate;
              if (_cursor.isNull(_cursorIndexOfEndDate)) {
                _tmpEndDate = null;
              } else {
                _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
              }
              final String _tmpType;
              _tmpType = _cursor.getString(_cursorIndexOfType);
              final Integer _tmpEpisodes;
              if (_cursor.isNull(_cursorIndexOfEpisodes)) {
                _tmpEpisodes = null;
              } else {
                _tmpEpisodes = _cursor.getInt(_cursorIndexOfEpisodes);
              }
              final String _tmpImageUrl;
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
              _tmpAnime = new AnimeEntity(_tmpAnimeId,_tmpTitle,_tmpScore,_tmpRank,_tmpPopularity,_tmpMembers,_tmpSynopsis,_tmpStartDate,_tmpEndDate,_tmpType,_tmpEpisodes,_tmpImageUrl);
              final ArrayList<GenreEntity> _tmpGenresCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfAnimeId);
              _tmpGenresCollection = _collectionGenres.get(_tmpKey_1);
              _item = new AnimeWithGenres(_tmpAnime,_tmpGenresCollection);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipgenreAscomExampleAnimedexDataLocalEntityGenreEntity(
      @NonNull final LongSparseArray<ArrayList<GenreEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshipgenreAscomExampleAnimedexDataLocalEntityGenreEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `genre`.`genre_id` AS `genre_id`,`genre`.`name` AS `name`,`genre`.`category` AS `category`,_junction.`anime_id` FROM `anime_genre` AS _junction INNER JOIN `genre` ON (_junction.`genre_id` = `genre`.`genre_id`) WHERE _junction.`anime_id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      // _junction.anime_id;
      final int _itemKeyIndex = 3;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfGenreId = 0;
      final int _cursorIndexOfName = 1;
      final int _cursorIndexOfCategory = 2;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<GenreEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final GenreEntity _item_1;
          final int _tmpGenreId;
          _tmpGenreId = _cursor.getInt(_cursorIndexOfGenreId);
          final String _tmpName;
          _tmpName = _cursor.getString(_cursorIndexOfName);
          final String _tmpCategory;
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
          _item_1 = new GenreEntity(_tmpGenreId,_tmpName,_tmpCategory);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
