package br.edu.cruzeirodosul;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.g2d.Sprite;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Principal implements ApplicationListener {
    // Atributos
    Texture backgroundTexture;
    Texture bucketTexture;
    Texture dropTexture;
    Sound dropSound;
    Music music;
    SpriteBatch spriteBatch;
    FitViewport viewport;
    Sprite bucketSprite;
    Vector2 touchPos;
    Array<Sprite> dropSprites;
    float dropTimer;
    Rectangle bucketRectangle;
    Rectangle dropRectangle;

    @Override
    public void create() {
        // Instanciação e configuração de atributos
        backgroundTexture = new Texture("background.png");
        bucketTexture = new Texture("bucket.png");
        dropTexture = new Texture("drop.png");
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8,5);
        // Instanciação e configuração do sprite do balde:
        bucketSprite = new Sprite(bucketTexture);
        bucketSprite.setSize(1,1);

        touchPos = new Vector2();
        dropSprites = new Array<Sprite>();
        bucketRectangle = new Rectangle();
        dropRectangle = new Rectangle();

        // Configuração
        music.setLooping(true);
        music.setVolume(.5f);
        music.play();
    } // void create()

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        // Resize your application here. The parameters represent the new window size.
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    } // void render()
    private void input() {
        float speed = 4f;
        // Obtém o tempo de renderização de cada quadro
        float delta = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            bucketSprite.translateX( speed * delta);
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            bucketSprite.translateX( -speed * delta);
        }

        // Entrada por mouse ou toque
        if(Gdx.input.isTouched()){
            // Obtém as coordenadas de tela do toque ou do clique
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            // Converte as coordenadas de tela em coordenadas de mundo
            viewport.unproject(touchPos);
            // Atribui a coordenada X do toque ao meio do balde
            bucketSprite.setCenterX(touchPos.x);
        }
    }// void input()

       private void logic() {
        // Variáveis de praticidade
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        float bucketWidth = bucketSprite.getWidth();
        float bucketHeight = bucketSprite.getHeight();

        //Prega (clamp) o balde entre os limites do mundo
        bucketSprite.setX(MathUtils.clamp(bucketSprite.getX(), 0, worldWidth - bucketWidth));

        float delta = Gdx.graphics.getDeltaTime();

        // Aplicação da posição do balde ao retângulo do balde
           bucketRectangle.set(bucketSprite.getX(), bucketSprite.getY(), bucketWidth, bucketHeight);

        // Para cada gota do conjunto de gotas
           for(int i = dropSprites.size - 1; i >= 0; i --){
               Sprite dropSprite = dropSprites.get(i);
               float dropWidth = dropSprite.getWidth();
               float dropHeight = dropSprite.getHeight();
               // Desloca a gota para baixo
               dropSprite.translateY(-2f * delta);
               // Aplica a posição da gota do retângulo da gota
               dropRectangle.set(dropSprite.getX(), dropSprite.getY(), dropWidth, dropHeight);
               // Se a gota passar do limite da tela, retire a gota da coleção de gotas
               if(dropSprite.getY() < -dropHeight){
                   dropSprites.removeIndex(i);
               } else if (bucketRectangle.overlaps(dropRectangle)) {
                    dropSprites.removeIndex(i);
                    dropSound.play();
               }
           }

           // Cria gotas
           dropTimer += delta;
           if(dropTimer > 1f){
               dropTimer = 0;
               createDroplet();
           }
    }// void logic()

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        spriteBatch.draw(backgroundTexture, 0,0,8,5);
        // Os sprites tem seu próprio método draw:
        bucketSprite.draw(spriteBatch);

        // Desenha as gotas do conjunto de gotas
            for(Sprite dropSprite : dropSprites){
                dropSprite.draw(spriteBatch);
            }
        spriteBatch.end();
    }// void draw()

    private void createDroplet(){
        // Variáveis de conveniência
        float dropWidth = 1;
        float dropHeight = 1;
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        // Criação a configuração
        Sprite dropSprite = new Sprite(dropTexture);
        dropSprite.setSize(dropWidth, dropHeight);
        // Cria uma gota em uma posição aleatória
        dropSprite.setX(MathUtils.random(0f, worldWidth - dropWidth));
        dropSprite.setY(worldHeight);
        // Adiciona a gota ao conjunto de gotas
        dropSprites.add(dropSprite); // Não esqueça dessa linha!

    } // void createDroplet()

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        // Destroy application's resources here.
    }
}



