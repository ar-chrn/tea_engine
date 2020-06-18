# Tea Engine
2D игровой движок на Java

## Демонстрация

Игра [Elf Adventures] демонстрирует возможности движка.

## Возможности Tea Engine

- Организация хода игры
- Работа со спрайтами
- Рендеринг изображений
- Анимации
- Инструменты для создания игрового графического интерфейса
- Обработка столкновений
- Работа с вводом с клавиатуры и мыши
- Возможность для разработчика игры реагировать на события игрового мира

## Принцип работы

Игра состоит из **сцен**. Сценой может быть, например, один уровень игры или один экран меню.

Каждая сцена содержит в себе **игровые объекты**. Например, в сцене [Level1] игры [Elf Adventures] в конструкторе 
на сцену добавляются начальные игровые объекты: игрок (эльф), комната, монеты, «спавнеры» противников.

    class Level1 extends Scene
    {
        private Elf player;

        Level1()
        {
            addPlayer();
            setupCamera();
            addRoom();
            addSpawners();
            addCoins();
        }

        private void addPlayer()
        {
            player = addGameObject(new Elf());      //      <———
        }

        // ...
    }

Сами по себе игровые объекты ничего не делают. За их поведение отвечают **компоненты**. У каждого игрового 
объекта может быть сразу много компонентов. Каждый отвечает за свой аспект поведения игрового объекта. 
Таким образом архитектура движка располагает к использованию принципа единственной ответственности и к созданию 
простого чистого кода.

Например, в игровом объекте [Elf] добавляются 6 компонентов. Из них 3 являются частью игрового движка: рендерер для отображения, 
коллайдер для обработки столкновений, аниматор для анимирования изображения. Другие 3 созданы разработчиком игры: 
ElfMovement для передвижения, ElfShoot для стрельбы, ElfCoins для подбора монет.

Также, у любого игрового объекта по умолчанию есть компонент Transform. Он определяет положение игрового объекта в пространстве.

    class Elf extends GameObject
    {
        Elf()
        {
            addComponent(new SpriteRenderer()).setLayer(Layers.PLAYER.ordinal());
            addComponent(new CircleCollider(0.5f, new Vector2(0, -0.15f)));
            addComponent(new Animator(Animations.elf().idle()));
            addComponent(new ElfMovement());
            addComponent(new ElfShoot());
            addComponent(new ElfCoins());
        }
    }

Компоненты позволяют реагировать на игровые события, происходящие с игровым объектом.

Например, компонент [ElfCoins] реагирует на игровые события *start* и *onCollisionEnter*. *start* вызывается при запуске сцены 
либо при добавлении игрового объекта на сцену. *onCollisionEnter* вызывается при коллизии с другим игровым объектом, если у 
обоих есть компонент Collider.

    class ElfCoins extends Component
    {
        private int coins;
        private CoinDisplay coinDisplay;

        @Override
        protected void start()
        {
            coins = 0;
        }

        @Override
        protected void onCollisionEnter(Collider other)
        {
            GameObject otherGO = other.getGameObject();
            if (otherGO instanceof Coin)
            {
                coins += 1;
                updateCoinDisplay();
                otherGO.destroy();
                otherGO.setActive(false);
            }
        }
        
        //...
    }
    
Всего доступно 6 игровых событий для компонентов:

- *start* вызывается при запуске сцены или при добавлении игрового объекта на сцену
- *update* вызывается каждый кадр
- *lateUpdate* вызывается каждый кадр после того, как все *update* у всех игровых объектов на сцене уже вызваны
- *onDestroy* вызывается перед уничтожением игрового объекта
- *onCollisionEnter* вызывается при начале коллизии с другим игровым объектом
- *onCollisionExit* вызывается при окончании коллизии с другим игровым объектом

И 2 игровых события для игровых объектов:

- *onAddedToScene* вызывается при добавлении игрового объекта на сцену
- *onDestroy* вызывается при уничтожении игрового объекта

[Elf Adventures]: https://github.com/ar-chrn/elf_adventures
[Elf]: https://github.com/ar-chrn/elf_adventures/blob/master/src/archrn/tea_engine/games/elf_adventures/Elf.java
[Level1]: https://github.com/ar-chrn/elf_adventures/blob/master/src/archrn/tea_engine/games/elf_adventures/Level1.java
[ElfCoins]: https://github.com/ar-chrn/elf_adventures/blob/master/src/archrn/tea_engine/games/elf_adventures/ElfCoins.java
