package com.example.carnotaturismo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.carnotaturismo.services.MusicService
import com.example.carnotaturismo.viewModel.TurismoAppModel
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Actividad principal de la aplicación.
 */
class MainActivity : AppCompatActivity() {
    /**
     * Controlador de navegación.
     */
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // navControler
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navController = navHostFragment!!.findNavController()

        // Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // BottomNavigationView
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)

        // Asegurar que, si estamos en un destino que no está en el menú (ej. VerTodosFragment),
        // seleccionar una pestaña del BottomNavigationView lleve siempre al destino correspondiente.
        bottomNav.setOnItemSelectedListener { item ->
            // Intentar comportamiento por defecto
            val handled = NavigationUI.onNavDestinationSelected(item, navController)
            if (handled) {
                true
            } else {
                // Si no se pudo navegar directamente, hacer popBackStack hasta ese destino
                // Aplica dialogo avanzado
                when (item.itemId) {
                    R.id.inicioFragment -> {
                        navController.popBackStack(R.id.inicioFragment, false)
                        true
                    }
                    R.id.lugarFragment -> {
                        navController.popBackStack(R.id.lugarFragment, false) || NavigationUI.onNavDestinationSelected(item, navController)
                    }
                    R.id.itinerarioListaFragment -> {
                        navController.popBackStack(R.id.itinerarioListaFragment, false) ||
                            NavigationUI.onNavDestinationSelected(item, navController)
                    }
                    R.id.mapaGeneralFragment -> {
                        navController.popBackStack(R.id.mapaGeneralFragment, false) ||
                            NavigationUI.onNavDestinationSelected(item, navController)
                    }
                    R.id.favoritosFragment -> {
                        navController.popBackStack(R.id.favoritosFragment, false) ||
                            NavigationUI.onNavDestinationSelected(item, navController)
                    }
                    else -> false
                }
            }
        }

        // Si el usuario re-selecciona una pestaña ya seleccionada, hacer pop hasta esa pantalla (comportamiento esperado)
        bottomNav.setOnItemReselectedListener { item ->
            navController.popBackStack(item.itemId, false)
        }

        // Observa la preferencia de música al arrancar la app y arranca/para el servicio según corresponda
        val appModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TurismoAppModel::class.java)
        appModel.musica.observe(this) { activo ->
            val intent = Intent(this, MusicService::class.java)
            if (activo) startService(intent) else stopService(intent)
        }

        // Observar cambios de idioma y recrear sólo si cambia realmente
        appModel.idioma.observe(this) { nuevo ->
            val currentTags =
                androidx.appcompat.app.AppCompatDelegate
                    .getApplicationLocales()
                    .toLanguageTags()
            val newTag =
                com.example.carnotaturismo.util.LocaleHelper
                    .tagForName(nuevo) ?: ""
            if (newTag != currentTags) {
                recreate()
            }
        }
    }

    /**
     * Crea el menú de opciones.
     *
     * @param menu Menú de opciones.
     * @return true si se creó el menú de opciones.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Maneja la selección de elementos del menú de opciones.
     *
     * @param item Elemento del menú seleccionado.
     * @return true si se manejó la selección.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        NavigationUI.onNavDestinationSelected(
            item,
            navController,
        ) ||
            super.onOptionsItemSelected(item)
}
