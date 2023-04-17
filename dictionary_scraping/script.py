import requests
import json
from pathlib import Path

from bs4 import BeautifulSoup
import bs4


base_url = "https://dictionary.obspm.fr"
letters = "abcdefghijklmnopqrstuvwxyz"  # abcdefghijklmnopqrstuvwxyz"


def get_all_pages_with_starting_letter(searched_letter: str) -> list[str]:
    page_number: int = 0
    url = (
        f"{base_url}/index.php?showAll=1&&search={searched_letter}&&page={page_number}"
    )
    page = requests.get(url)
    page.encoding = "utf-8"

    html_page = page.text
    soup = BeautifulSoup(html_page, "lxml")
    available_pages = soup.find_all("p", "center")[0]
    available_pages = available_pages.find_all("a")
    o = []
    bad = ["", ">", ">>"]
    for a in available_pages:
        if a.text not in bad:
            o.append(a["href"])
    return [a["href"] for a in available_pages]


def get_page_content(url: str) -> str:
    page = requests.get(url)
    page.encoding = "utf-8"
    return page.text


def get_words(rows: bs4.element.ResultSet) -> dict:
    words = {}
    for row in rows:
        term = row.find("div", class_="title0").get_text(strip=True).replace("\n", "")
        title_1 = (
            row.find("div", class_="title1").get_text(strip=True).replace("\n", "")
        )
        title_2 = (
            row.find("div", class_="title2").get_text(strip=True).replace("\n", "")
        )
        title_3 = (
            row.find("div", class_="title3").get_text(strip=True).replace("\n", "")
        )

        margin_top_description: str = (
            ""
            if row.find(class_="marginTop description") is None
            else row.find(class_="marginTop description")
            .get_text(strip=True)
            .replace("\n", "")
        )
        margin_top_ety_EN: str = (
            ""
            if row.find(class_="marginTop ety_EN") is None
            else row.find(class_="marginTop ety_EN")
            .get_text(strip=True)
            .replace("\n", "")
        )
        margin_top_ety_PE: str = (
            ""
            if row.find(class_="marginTop ety_PE") is None
            else row.find(class_="marginTop ety_PE")
            .get_text(strip=True)
            .replace("\n", "")
        )
        margin_top_voir: str = (
            ""
            if row.find(class_="marginTop voir") is None
            else row.find(class_="marginTop voir")
            .get_text(strip=True)
            .replace("\n", "")
        )
        meaning: str = (
            margin_top_description
            + margin_top_ety_EN
            + margin_top_ety_PE
            + margin_top_voir
        )

        words.update(
            {
                f"{term}": {
                    "title_1": title_1,
                    "title_2": title_2,
                    "title_3": title_3,
                    "meaning": meaning,
                }
            }
        )
    return words


def main() -> None:
    words = {}

    for letter in letters:
        available_pages = get_all_pages_with_starting_letter(letter)

        for available_page in available_pages:
            print(f"{letter}: {base_url}{available_page}")
            page: str = get_page_content(f"{base_url}{available_page}")
            soup: BeautifulSoup = BeautifulSoup(page, "lxml")
            rows: bs4.element.ResultSet = soup.find_all("tr")
            new_words = get_words(rows=rows)
            words.update(new_words)

    with open("word.json", "w") as f:
        json.dump(words, f)
    # with open("word.json", "r") as f:
    #     b: dict = json.load(f)


if __name__ == "__main__":
    main()
