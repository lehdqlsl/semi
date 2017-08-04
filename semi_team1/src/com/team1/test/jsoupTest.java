package com.team1.test;

import java.io.IOException;
import java.util.ArrayList;

import javax.crypto.CipherInputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class jsoupTest {
	public static void main(String[] args) {
		try {

<<<<<<< HEAD
			Document doc = Jsoup.connect("http://movie.naver.com/movie/bi/mi/basic.nhn?code=146469").get();
			Elements title = doc.select(".rank_keyword li");
			Elements date = doc.select(".tx_info");
			String today = date.text();
			ArrayList<String> list[] = new ArrayList[3];
			for (int i = 0; i < 3; i++) {
=======
			/*
			 * Document doc =
			 * Jsoup.connect("http://movie.naver.com/movie/sdb/rank/rmovie.nhn")
			 * .get(); Elements title = doc.select(".title");
			 * 
			 * ArrayList<String> list1 = new ArrayList<>();
			 * 
			 * for (Element e : title) { System.out.println(e.text());
			 * list1.add(e.text()); }
			 * 
			 * Elements rank = doc.select(".r_ranking li a");
			 * System.out.println(rank.text());
			 */

			/////////////////////////////////////////////////////////////////////////////////////////////////////////
			Document doc1 = Jsoup.connect("http://score.sports.media.daum.net/record/soccer/epl/trnk.daum").get();
			Elements test1 = doc1.select("tbody");

			String str[] = test1.text().split(" ");
			ArrayList<String> list[] = new ArrayList[20];
			int z = 0;
			for (int i = 0; i < 20; i++) {
>>>>>>> branch 'master' of https://github.com/lehdqlsl/semi
				list[i] = new ArrayList<>();
<<<<<<< HEAD
			}
			int i = 0;
			int j = 0;
			for (Element node : title) {
				if (i == 10) {
					j++;
					i = 0;
=======
				for (int j = 0; j < 11; j++) {
					if (str[z].contains("W") || str[z].contains("C")) {
						z++;
					}
					list[i].add(str[z]);
					z++;
>>>>>>> branch 'master' of https://github.com/lehdqlsl/semi
				}
				list[j].add(node.text());
				i++;
			}

<<<<<<< HEAD
			System.out.println(today);
			System.out.println("영화 인기검색어");
			for (String s : list[0]) {
				String per = s.substring(s.length() - 5, s.length());
				String movie = s.substring(0, s.length() - 5);
				System.out.println(movie);
				System.out.println(per);
			}
			System.out.println("영화인 인기검색어");
			for (String s : list[1]) {
				String per = s.substring(s.length() - 5, s.length());
				String movie = s.substring(0, s.length() - 5);
				System.out.println(movie);
				System.out.println(per);
			}
			System.out.println("티켓예매순");
			for (String s : list[2]) {
				String per = s.substring(s.length() - 6, s.length());
				String movie = s.substring(0, s.length() - 6);
				System.out.println(movie);
				System.out.println(per);
=======
			list[9].set(1, "W.브로미치");
			list[13].set(1, "W.팰리스");

			for (String s : list[9]) {
				System.out.println(s);
>>>>>>> branch 'master' of https://github.com/lehdqlsl/semi
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
