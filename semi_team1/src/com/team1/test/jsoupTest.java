package com.team1.test;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class jsoupTest {
	public static void main(String[] args) {
		try {

			Document doc = Jsoup.connect("http://movie.naver.com/movie/sdb/rank/rmovie.nhn").get();
			Elements title = doc.select(".title");

			ArrayList<String> list1 = new ArrayList<>();

			for (Element e : title) {
				System.out.println(e.text());
				list1.add(e.text());
			}

			Elements rank = doc.select(".r_ranking li a");
			System.out.println(rank.text());

			/////////////////////////////////////////////////////////////////////////////////////////////////////////
			Document doc1 = Jsoup.connect("http://score.sports.media.daum.net/record/soccer/epl/trnk.daum").get();
			Elements test1 = doc1.select("tbody");

			String str[] = test1.text().split(" ");
			ArrayList<String> list[] = new ArrayList[20];
			int z = 0;
			for (int i = 0; i < 20; i++) {

				list[i] = new ArrayList<>();
				for (int j = 0; j < 11; j++) {
					list[i].add(str[z]);
					z++;
				}
			}

			for (String str1 : list[0]) {
				System.out.println(str1);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
