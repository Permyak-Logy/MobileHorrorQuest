package ru.pyply.games.mobilehorrorquest;

public class Story {

    public Situation current_situation;

    Story() {
        Situation start_story, drink_tea, open_doors, main_door, back_door,
                boom_back_door, back_door_room, break_out_door, end;

        start_story = new Situation(
                "Письмо",
                "Вы только что получили письмо. Вам предлагают расследовать серийные убийства в старом заброшенном "
                        + "доме страха.\n"
                        + "1. Возьмусь за дело.\n"
                        + "2. Пойду лучше чайку попью, чем нервы трепать.",
                2, 0, 0
        );
        drink_tea = new Situation(
                "Чай и гибель",
                "Ты напился чайку, но по твою душу и пришёл этот " +
                        "маньяк убив через неделю после получения этого письма.",
                0, -3, 0
        );

        open_doors = new Situation(
                "Вход в дом страха",
                "Можете войти с чёрного хода или с главного\n" +
                        "1. Войти через главный ход\n" +
                        "2. Войти через чёрный ход\n" +
                        "3. Вернуться с докладом.",
                3, 0, 0
        );
        start_story.direction[0] = open_doors;
        start_story.direction[1] = drink_tea;


        main_door = new Situation(
                "Главный зал",
                "Вы увидели на стенах трупы жертв этого маньяка. Уже прорыв! Можно их позже исследовать\n" +
                        "1. Выйти из здания",
                1, 0, 40
        );
        main_door.direction[0] = open_doors;

        back_door = new Situation(
                "Задний ход",
                "Чёрт. Кто-то захлопнул прямо передо мной дверь. Надо что-то придумать!\n" +
                        "1. Выломать дверь\n" +
                        "2. Вскрыть замок\n" +
                        "3. Взорвать дверь из мини бомб\n" +
                        "4. Вернуться",
                4, 0, 10
        );
        end = new Situation(
                "Возвращение",
                "Вы вернулись домой.",
                0, 0, 0
        );

        open_doors.direction[2] = end;
        open_doors.direction[0] = main_door;
        open_doors.direction[1] = back_door;

        boom_back_door = new Situation(
                "Взрыв!",
                "Ох нет! Бомбы взорвались прежде чем вы отошли на безопасное растояние! Вас разорвало!",
                0, -3, 0
        );
        back_door_room = new Situation(
                "Задняя часть парка",
                "Вы вошли в заднюю часть этого сдания и обнаружили записки маньяка. Это уже прорыв для расследования.\n" +
                        "1. Выйти из дома",
                1, 0, 60

        );
        back_door.direction[0] = back_door_room;
        back_door.direction[1] = back_door_room;
        back_door.direction[2] = boom_back_door;
        back_door.direction[3] = open_doors;

        back_door_room.direction[0] = open_doors;

        current_situation = start_story;
    }

    public void go(int num) {
        if (num <= current_situation.direction.length) {
            current_situation = current_situation.direction[num - 1];
        } else
            System.out.println("Вы можете выбирать из "
                    + current_situation.direction.length + " вариантов");
    }

    public boolean isEnd() {
        return current_situation.direction.length == 0;
    }
}