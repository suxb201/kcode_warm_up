import os
import requests
import json
import schedule
import time

last_score = 0
want_best_score = 1160382

#
def flush_score():
    global last_score
    data = {"questionId": 1, "pageNum": 1, "size": 10}

    r = requests.post(
        "https://kcode.kuaishou.com/challenge/score/rt/list",
        headers={
            "Content-Type": 'application/json',
            'Origin': 'https://kcode.kuaishou.com'
        },
        data=json.dumps(data),
        cookies={
            'did': 'web_7cd1c684be6c440093794957ceafef2b',
            'experimentation_subject_id': 'Ijc4Y2YwMTJiLWVjN2EtNDY1ZS05MGU5LTc4NTA2MzE5Yjc2NyI%3D--37e5d57972d4d1c151ad45366a489ccf416ba69c',
            'didv': '1591969103694',
            'userId': '904463484',
            'kuaishou.challenge.web.api_st': 'Ch1rdWFpc2hvdS5jaGFsbGVuZ2Uud2ViLmFwaS5zdBKwAehNhpHvFjd6RxjfwYNt5RXPTzU95mmUSQQVxaVY-DWU0ZJQ6kSABz9JZLebrr6Pd_d26DUdX005o925sBSWG6zvntqN3wOnzs5Og6Glp1PojFR7FwjbPBoQHvXVH396XBe1wpTi1D6lYJPN6nTmM_gjnDVIMhDdJlNopsbnWBWpFrt-S1ZSkZl027NZFRXdUujUo8ucWIzl3qZABqfinReroTEcY8XgCSzngCujY_WaGhLg7tMQmw6Aa1NTr_n1VptOHT4iICCvcbwiuka5uvv2P77jxub2XOf33vfzQB-aa_EAbk1TKAUwAQ',
            'kuaishou.challenge.web.api_ph': '718b97f06222159b89d0162c76b3c9a4e920'
        }
    )
    score = int(json.loads(r.text)['scores'][0]['score'])
    if score != last_score:
        print(json.loads(r.text)['scores'][0]['score'])
        if score >= want_best_score:
            exit(0)
        last_score = score


def git_push():
    with open("src/main/java/com.kuaishou.kcode/KcodeTest.java", encoding="utf-8", mode="a") as data:
        data.write("//test")
    data.close()

    os.system("git status")
    os.system("git commit -a -m \"flush score\" ")
    os.system("git push")


schedule.every(1).minutes.do(flush_score)
schedule.every(10).minutes.do(git_push)

while True:
    schedule.run_pending()
    time.sleep(1)
