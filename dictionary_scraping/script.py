import requests
from bs4 import BeautifulSoup
from enum import Enum


class Settings(Enum):
    base_url = "https://dictionary.obspm.fr"


class Word:
    def __init__(
        self,
        term: str,
        title_1: str,
        title_2: str,
        title_3: str,
        margin_top_description: str,
        margin_top_ety_EN: str,
        margin_top_ety_PE: str,
    ) -> None:
        self.term = term
        self.title_1 = title_1
        self.title_2 = title_2
        self.title_3 = title_3
        self.margin_top_description = margin_top_description
        self.margin_top_ety_EN = margin_top_ety_EN
        self.margin_top_ety_PE = margin_top_ety_PE

    def to_dict(self):
        return {
            self.term: [
                self.title_1,
                self.title_2,
                self.title_3,
                self.margin_top_description,
                self.margin_top_ety_EN,
                self.margin_top_ety_PE,
            ]
        }


def get_all_pages_with_starting_letter(searched_letter: str) -> list[str]:
    page_number: int = 0
    url = f"{Settings.base_url}/index.php?showAll=1&&search={searched_letter}&&page={page_number}"
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


def get_page():
    ...


def main() -> None:
    letters: str = "abcdefghijklmnopqrstuvwxyz"

    for letter in letters:
        available_pages = get_all_pages_with_starting_letter(letter)
        for available_page in available_pages:
            print(f"{Settings.base_url}{available_page}")


if __name__ == "__main__":
    # main()
    pass
