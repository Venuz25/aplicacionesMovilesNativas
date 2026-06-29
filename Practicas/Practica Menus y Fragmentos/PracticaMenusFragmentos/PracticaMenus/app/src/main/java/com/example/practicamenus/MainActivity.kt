package com.example.practicamenus

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Configuración del Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 2. Configuración del Drawer Layout (Menú Lateral)
        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_perfil -> mostrarMensaje("Mi Perfil")
                R.id.nav_contactos -> mostrarMensaje("Contactos")
                R.id.nav_ajustes -> mostrarMensaje("Ajustes")
                R.id.nav_acerca -> mostrarMensaje("Acerca de...")
                R.id.nav_salir -> finish()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // 3. Configuración del Bottom Navigation (Menú Inferior y Fragmentos)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_inicio -> {
                    // CORRECCIÓN 1: Ahora carga HomeFragment (Galería + Calendario)
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.bottom_favoritos -> {
                    // CORRECCIÓN 2: Ya no carga el calendario, solo muestra un mensaje
                    mostrarMensaje("Sección de Favoritos")
                    true
                }
                R.id.bottom_configuracion -> {
                    mostrarMensaje("Configuración en construcción")
                    true
                }
                else -> false
            }
        }

        // 4. Cargar fragmento inicial (Evita que la pantalla esté blanca al abrir la app)
        if (savedInstanceState == null) {
            // CORRECCIÓN 3: Al abrir la app, también debe cargar HomeFragment por defecto
            replaceFragment(HomeFragment())
            bottomNav.selectedItemId = R.id.bottom_inicio
        }
    }

    // Función clave para intercambiar los fragmentos en el FragmentContainerView
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}