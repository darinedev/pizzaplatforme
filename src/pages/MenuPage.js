import React, { useState } from 'react';
import PizzaCard from '../components/PizzaCard';
import LoadingSpinner from '../components/LoadingSpinner';

const MenuPage = ({ 
  pizzas, 
  loading, 
  error, 
  onAddToCart, 
  onRetry 
}) => {
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('all');

  // Filtrage des pizzas
  const filteredPizzas = pizzas.filter(pizza => {
    const matchesSearch = pizza.nom.toLowerCase().includes(searchTerm.toLowerCase()) ||
      pizza.ingredients.some(ingredient => 
        ingredient.toLowerCase().includes(searchTerm.toLowerCase())
      );
    
    const matchesCategory = selectedCategory === 'all' || 
      pizza.categorie === selectedCategory;

    return matchesSearch && matchesCategory;
  });

  // Catégories disponibles
  const categories = [
    { id: 'all', nom: 'Toutes', icon: '🍕' },
    { id: 'classique', nom: 'Classiques', icon: '🍅' },
    { id: 'vegetarienne', nom: 'Végétariennes', icon: '🥬' },
    { id: 'viande', nom: 'Viandes', icon: '🥓' },
    { id: 'fromage', nom: 'Fromages', icon: '🧀' },
    { id: 'speciale', nom: 'Spéciales', icon: '⭐' }
  ];

  if (loading) {
    return (
      <div className="container mx-auto px-4 py-8">
        <LoadingSpinner message="Chargement de notre délicieux menu..." />
      </div>
    );
  }

  if (error) {
    return (
      <div className="container mx-auto px-4 py-8">
        <div className="text-center py-12">
          <div className="text-6xl mb-4">😢</div>
          <h2 className="text-2xl font-bold text-gray-800 mb-4">Oups ! Une erreur est survenue</h2>
          <p className="text-gray-600 mb-6">{error}</p>
          <button 
            onClick={onRetry}
            className="bg-red-500 text-white px-6 py-3 rounded-lg hover:bg-red-600 transition-colors"
          >
            Réessayer
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-4 py-8">
      {/* Header de la page */}
      <div className="text-center mb-12">
        <h1 className="text-5xl font-bold text-gray-800 mb-4">
          Notre Menu
          <span className="text-red-500">.</span>
        </h1>
        <p className="text-xl text-gray-600 max-w-3xl mx-auto leading-relaxed">
          Découvrez nos délicieuses pizzas artisanales préparées avec des ingrédients frais 
          et de qualité supérieure. Chaque pizza est une invitation au voyage culinaire !
        </p>
      </div>

      {/* Barre de recherche */}
      <div className="max-w-md mx-auto mb-8">
        <div className="relative">
          <input
            type="text"
            placeholder="Rechercher une pizza, un ingrédient..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="w-full px-4 py-3 pl-12 border border-gray-300 rounded-full focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent shadow-sm"
          />
          <div className="absolute left-4 top-1/2 transform -translate-y-1/2 text-gray-400">
            🔍
          </div>
          {searchTerm && (
            <button
              onClick={() => setSearchTerm('')}
              className="absolute right-4 top-1/2 transform -translate-y-1/2 text-gray-400 hover:text-gray-600"
            >
              ✕
            </button>
          )}
        </div>
      </div>

      {/* Filtres par catégorie */}
      <div className="flex flex-wrap justify-center gap-3 mb-12">
        {categories.map(category => (
          <button
            key={category.id}
            onClick={() => setSelectedCategory(category.id)}
            className={`flex items-center space-x-2 px-4 py-2 rounded-full transition-all duration-200 ${
              selectedCategory === category.id
                ? 'bg-red-500 text-white shadow-lg transform scale-105'
                : 'bg-white text-gray-700 hover:bg-gray-50 border border-gray-200 hover:border-red-300'
            }`}
          >
            <span>{category.icon}</span>
            <span className="font-medium">{category.nom}</span>
          </button>
        ))}
      </div>

      {/* Statistiques */}
      <div className="text-center mb-8">
        <p className="text-gray-600">
          {filteredPizzas.length} pizza{filteredPizzas.length > 1 ? 's' : ''} trouvée{filteredPizzas.length > 1 ? 's' : ''}
          {searchTerm && ` pour "${searchTerm}"`}
          {selectedCategory !== 'all' && ` dans la catégorie "${categories.find(c => c.id === selectedCategory)?.nom}"`}
        </p>
      </div>

      {/* Grille des pizzas */}
      {filteredPizzas.length > 0 ? (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8">
          {filteredPizzas.map(pizza => (
            <PizzaCard
              key={pizza.id}
              pizza={pizza}
              onAddToCart={onAddToCart}
            />
          ))}
        </div>
      ) : (
        <div className="text-center py-16">
          <div className="text-6xl mb-4">🔍</div>
          <h3 className="text-2xl font-bold text-gray-800 mb-4">Aucune pizza trouvée</h3>
          <p className="text-gray-600 mb-6">
            {searchTerm 
              ? `Aucune pizza ne correspond à votre recherche "${searchTerm}"`
              : `Aucune pizza disponible dans cette catégorie`
            }
          </p>
          <div className="space-x-4">
            {searchTerm && (
              <button
                onClick={() => setSearchTerm('')}
                className="bg-red-500 text-white px-6 py-3 rounded-lg hover:bg-red-600 transition-colors"
              >
                Effacer la recherche
              </button>
            )}
            <button
              onClick={() => {
                setSearchTerm('');
                setSelectedCategory('all');
              }}
              className="bg-gray-500 text-white px-6 py-3 rounded-lg hover:bg-gray-600 transition-colors"
            >
              Voir toutes les pizzas
            </button>
          </div>
        </div>
      )}

      {/* Section promotionnelle */}
      {filteredPizzas.length > 0 && searchTerm === '' && selectedCategory === 'all' && (
        <div className="mt-16 bg-gradient-to-r from-red-500 to-orange-500 text-white rounded-2xl p-8 text-center">
          <h2 className="text-3xl font-bold mb-4">🎉 Offre Spéciale !</h2>
          <p className="text-xl mb-4">
            Livraison gratuite pour toute commande supérieure à 20€
          </p>
          <p className="opacity-90">
            Profitez-en dès maintenant pour vous régaler !
          </p>
        </div>
      )}
    </div>
  );
};

export default MenuPage;