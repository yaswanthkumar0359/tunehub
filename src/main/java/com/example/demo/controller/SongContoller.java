package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Song;
import com.example.demo.services.SongService;


@Controller
public class SongContoller
{
	@Autowired
	SongService ss;

	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song)
	{
		boolean songStatus = ss.songExists(song.getName());
		if(songStatus == false)
		{
			ss.addSong(song);
			System.out.println("Song added sucuessfully");
		}
		else
		{
			System.out.println("Song already exists");
		}
		return "adminhome";
	}
	@GetMapping("/viewSongs")
	public String viewSongs(Model model) 
	{
		List<Song>songsList=ss.fetchAllSongs();
		model.addAttribute("songs", songsList);
		return "displaySongs";
	}

	@GetMapping("/playSongs")
	public String playSongs(Model model) 
	{
		boolean premiumUser=false;

		if(premiumUser == true)
		{
			List<Song>songsList=ss.fetchAllSongs();
			model.addAttribute("songs", songsList);
			return "displaySongs";
		}
		else
		{
			return "makePayment";
		}

	}
}
