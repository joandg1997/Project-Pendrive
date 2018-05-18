﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;

public class Player : MonoBehaviour {

    public Rigidbody2D rb2d;
    public Animator anim;
	public ParticleSystem part;
	public SpriteRenderer spri;
    public AudioSource explosionSound;
    public AudioSource coinSound;
    public float velocidad = 10;
    public float velocidadMaxima = 5;
    public float fuerzaSalto = 50;
    public int puntosMonedas = 10;
    public int puntosPrinter = 100;
    private AudioSource[] sounds;
    private bool isGrounded;
    private float direccion;


    // Use this for initialization
    void Start () {
        rb2d = GetComponent<Rigidbody2D>();
        anim = GetComponent<Animator>();
		part = GetComponent<ParticleSystem>();
		spri = GetComponent<SpriteRenderer>();
        sounds = GetComponents<AudioSource>();

    }

    // Update is called once per frame
    void Update () {

	}

    // FixedUpdate is called fixed in time
    private void FixedUpdate()
    {
        if (Input.GetAxis("Horizontal") != 0)
        {
            direccion = Input.GetAxis("Horizontal");
            MovimientoHorizontal(direccion);
            anim.SetFloat("Direction", direccion);
            anim.SetBool("Moving", true);
        }
        else
        {
            anim.SetBool("Moving", false);
            if (isGrounded == true)
            {
                rb2d.velocity = new Vector2(rb2d.velocity.x * 0.9f, rb2d.velocity.y);
            }

        }

        if (Input.GetKeyDown(KeyCode.UpArrow))
        {
            Salto();
        }
    }

    /// <summary>
    /// Control the X horizontal move
    /// </summary>
    /// <param name="dirreccion">X direction of movement, negative left and positive right</param>
    /// <author>Daniil Digtyar</author>
    void MovimientoHorizontal(float dirreccion)
    {
        if (rb2d.velocity.x < velocidadMaxima && rb2d.velocity.x > -velocidadMaxima)
        {
            rb2d.AddForce(new Vector2(dirreccion * velocidad, 0), ForceMode2D.Force);
        }
    }

    /// <summary>
    /// Player jump control
    /// fuerzaSalto controls the jump force
    /// </summary>
    /// <remarks>If you jump in the air, control that the terrain have the tag "Suelo"</remarks>
    /// <author>Daniil Digtyar</author>
    void Salto()
    {
        if (isGrounded == true)
        {
            rb2d.AddForce(new Vector2(0, fuerzaSalto), ForceMode2D.Impulse);
        }

    }

    /// <summary>
    /// Control when you are in the ground
    /// </summary>
    /// <param name="collision"></param>
    /// <author>Daniil Digtyar</author>
    private void OnCollisionStay2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Suelo")
        {
            isGrounded = true;
        }
    }

    /// <summary>
    /// Control when you leave the gound
    /// </summary>
    /// <param name="collision"></param>
    /// <author>Daniil Digtyar</author>
    private void OnCollisionExit2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Suelo")
        {
            isGrounded = false;
        }
    }

    /// <summary>
    /// Add points, destroy or load when collect collectables.
    /// </summary>
    /// <param name="collision"></param>
    /// <remarks>Make sure that the coin have the tag "Coin" and the box collider is trigger</remarks>
    /// <author>Daniil Digtyar</author>
	private IEnumerator OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Coin")
        {
			if(!Globals.muted)
            	sounds[0].Play();
            Globals.puntos += puntosMonedas;
            Destroy(collision.gameObject);
        }

        if (collision.gameObject.tag == "Printer")
        {
            Globals.puntos += puntosPrinter;
			if(!Globals.muted)
           		sounds[0].Play();
            Destroy(collision.gameObject);
            if(collision.name.Equals("PrinterPieceThree"))
            {
                Invoke("LoadWinLevel", 1f);
            }
            else
            {
                Invoke("LoadNextLevel", 1f);
            }
        }

        if (collision.gameObject.tag == "PitFall")
        {	
			part.Play();
			if(!Globals.muted)
            	sounds[1].Play();
			spri.enabled = false;

			yield return new WaitForSeconds(1);
			Globals.vidas -= 1;
			if (Globals.vidas.Equals(0))
			{
				GameOver();
			}
			else
			{
				SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
			}

        }
    }

    /// <summary>
    /// Is called when lives is 0 
    /// </summary>
    private void GameOver()
    {
        Application.LoadLevel("MenuLost");
    }

    /// <summary>
    /// Is called when need to load next level
    /// </summary>
    private void LoadNextLevel()
    {
        Globals.actualLevel += 1;
        Application.LoadLevel("Win" + Globals.actualLevel);
    }
    
    /// <summary>
    /// Is called when you collected the third and last piece of the printer.
    /// </summary>
    private void LoadWinLevel()
    {
        Application.LoadLevel("MenuWin");
    }
}
