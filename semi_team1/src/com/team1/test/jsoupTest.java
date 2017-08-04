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

			Document doc = Jsoup.connect("http://movie.naver.com/movie/bi/mi/basic.nhn?code=146469").get();
			Elements title = doc.select(".rank_keyword li");
			Elements date = doc.select(".tx_info");
			String today = date.text();
			ArrayList<String> list[] = new ArrayList[3];
			for (int i = 0; i < 3; i++) {
				list[i] = new ArrayList<>();
			}
			int i = 0;
			int j = 0;
			for (Element node : title) {
				if (i == 10) {
					j++;
					i = 0;
				}
				list[j].add(node.text());
				i++;
			}

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
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
