import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useCart } from '../context/CartContext';

const Layout = ({ children }) => {
  const location = useLocation();
  const { getTotalItems } = useCart();
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  
  const totalItems = getTotalItems();

  const NavLink = ({ to, children, className = "" }) => (
    <Link
      to={to}
      className={`transition-colors duration-200 ${
        location.pathname === to
          ? 'text-yellow-300 font-semibold'
          : 'text-white hover:text-yellow-200'
      } ${className}`}
      onClick={() => setMobileMenuOpen(false)}
    >
      {children}
    </Link>
  );

  return (
    <div className="min-h-screen bg-gray-50 flex flex-col">
      {/* Header */}
      <header className="bg-red-600 text-white shadow-lg sticky top-0 z-50">
        <div className="container mx-auto px-4">
          <div className="flex justify-between items-center py-4">
            {/* Logo */}
            <Link 
              to="/" 
              className="flex items-center space-x-2 text-2xl font-bold hover:text-yellow-200 transition-colors"
            >
              <span className="text-3xl">🍕</span>
              <span>Bella Pizza</span>
            </Link>

            {/* Navigation Desktop */}
            <nav className="hidden md:flex items-center space-x-6">
              <NavLink to="/">Menu</NavLink>
              <NavLink to="/checkout" className="flex items-center">
                <span className="mr-1">🛒</span>
                Panier
                {totalItems > 0 && (
                  <span className="ml-2 bg-yellow-500 text-red-600 rounded-full px-2 py-1 text-sm font-bold">
                    {totalItems}
                  </span>
                )}
              </NavLink>
            </nav>

            {/* Menu burger mobile */}
            <button
              onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
              className="md:hidden flex flex-col items-center justify-center w-8 h-8 space-y-1"
            >
              <span className={`w-6 h-0.5 bg-white transition-all ${mobileMenuOpen ? 'rotate-45 translate-y-1.5' : ''}`}></span>
              <span className={`w-6 h-0.5 bg-white transition-all ${mobileMenuOpen ? 'opacity-0' : ''}`}></span>
              <span className={`w-6 h-0.5 bg-white transition-all ${mobileMenuOpen ? '-rotate-45 -translate-y-1.5' : ''}`}></span>
            </button>
          </div>

          {/* Navigation Mobile */}
          {mobileMenuOpen && (
            <nav className="md:hidden py-4 border-t border-red-500">
              <div className="flex flex-col space-y-4">
                <NavLink to="/" className="block">Menu</NavLink>
                <NavLink to="/checkout" className="flex items-center">
                  <span className="mr-2">🛒</span>
                  Panier
                  {totalItems > 0 && (
                    <span className="ml-2 bg-yellow-500 text-red-600 rounded-full px-2 py-1 text-sm font-bold">
                      {totalItems}
                    </span>
                  )}
                </NavLink>
              </div>
            </nav>
          )}
        </div>
      </header>

      {/* Contenu principal */}
      <main className="flex-1">
        {children}
      </main>

      {/* Footer */}
      <footer className="bg-gray-800 text-white mt-auto">
        <div className="container mx-auto px-4 py-8">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {/* Informations restaurant */}
            <div>
              <h3 className="text-lg font-semibold mb-4 flex items-center">
                <span className="mr-2">🍕</span>
                Bella Pizza
              </h3>
              <p className="text-gray-400 text-sm leading-relaxed">
                Les meilleures pizzas artisanales préparées avec des ingrédients frais et de qualité. 
                Livraison rapide dans toute la ville.
              </p>
            </div>

            {/* Contact */}
            <div>
              <h4 className="font-semibold mb-4 flex items-center">
                <span className="mr-2">📞</span>
                Contact
              </h4>
              <div className="space-y-2 text-sm text-gray-400">
                <p className="flex items-center">
                  <span className="mr-2">📍</span>
                  123 Rue de la Pizza, 75001 Paris
                </p>
                <p className="flex items-center">
                  <span className="mr-2">📞</span>
                  01 23 45 67 89
                </p>
                <p className="flex items-center">
                  <span className="mr-2">📧</span>
                  contact@bellapizza.fr
                </p>
              </div>
            </div>

            {/* Horaires */}
            <div>
              <h4 className="font-semibold mb-4 flex items-center">
                <span className="mr-2">🕒</span>
                Horaires
              </h4>
              <div className="space-y-1 text-sm text-gray-400">
                <p>Lundi - Jeudi : 11h - 23h</p>
                <p>Vendredi - Samedi : 11h - 00h</p>
                <p>Dimanche : 12h - 23h</p>
              </div>
            </div>
          </div>

          {/* Copyright */}
          <div className="border-t border-gray-700 mt-8 pt-6 text-center text-sm text-gray-400">
            <p>&copy; 2024 Bella Pizza. Tous droits réservés.</p>
            <p className="mt-1">Fait avec ❤️ pour les amateurs de pizza</p>
          </div>
        </div>
      </footer>
    </div>
  );
};

export default Layout;