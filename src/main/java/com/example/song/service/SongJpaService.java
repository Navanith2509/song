package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.song.model.Song;
import com.example.song.repository.SongJpaRepository;
import com.example.song.repository.SongRepository;

@Service
public class SongJpaService implements SongRepository {

	@Autowired
	private SongJpaRepository songjparepository;

	@Override
	public ArrayList<Song> getSongs() {
		List<Song> songdata = songjparepository.findAll();
		ArrayList<Song> getsongs = new ArrayList<>(songdata);
		return getsongs;
	}

	@Override
	public Song getSongById(int songId) {
		try {
			Song song = songjparepository.findById(songId).get();
			return song;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Song addSong(Song song) {
		songjparepository.save(song);
		int songID= song.getSongId();
		
		return songjparepository.findById(songID).get();

	}

	@Override
	public Song updateSong(int songId, Song song) {
		try {
			Song newBook = songjparepository.findById(songId).get();
			if (song.getSongName() != null) {
				newBook.setSongName(song.getSongName());
			}
			if (song.getLyricist() != null) {
				newBook.setLyricist(song.getLyricist());
			}
			if (song.getSongName() != null) {
				newBook.setSinger(song.getSinger());
			}
			if (song.getSongName() != null) {
				newBook.setMusicDirector(song.getMusicDirector());
			}
			return newBook;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteSong(int songId) {
		try {

			songjparepository.deleteById(songId);
		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

}
