import React, { useState } from 'react';
import { formatPrice } from '../utils/validation';

const PizzaCard = ({ pizza, onAddToCart }) => {
  const [isAdding, setIsAdding] = useState(false);

  const handleAddToCart = async () => {
    setIsAdding(true);
    onAddToCart(pizza);
    
    // Animation feedback
    setTimeout(() => {
      setIsAdding(false);
    }, 500);
  };

  return (
    <div className="bg-white rounded-lg shadow-md hover:shadow-lg transition-all duration-300 overflow-hidden group">
      {/* Image de la pizza */}
      <div className="h-48 bg-gradient-to-br from-red-400 to-red-600 flex items-center justify-center relative overflow-hidden">
        <span className="text-6xl group-hover:scale-110 transition-transform duration-300">
          🍕
        </span>
        <div className="absolute inset-0 bg-black opacity-0 group-hover:opacity-10 transition-opacity duration-300"></div>
      </div>

      {/* Contenu de la carte */}
      <div className="p-4">
        {/* Nom de la pizza */}
        <h3 className="text-xl font-bold text-gray-800 mb-2 group-hover:text-red-600 transition-colors">
          {pizza.nom}
        </h3>

        {/* Ingrédients */}
        <div className="mb-4">
          <p className="text-sm text-gray-600 leading-relaxed">
            {pizza.ingredients && pizza.ingredients.length > 0 
              ? pizza.ingredients.join(', ')
              : 'Ingrédients non spécifiés'
            }
          </p>
        </div>

        {/* Prix et bouton */}
        <div className="flex justify-between items-center">
          <span className="text-2xl font-bold text-red-600">
            {formatPrice(pizza.prix)}
          </span>
          
          <button
            onClick={handleAddToCart}
            disabled={isAdding}
            className={`px-4 py-2 rounded-lg font-semibold transition-all duration-200 ${
              isAdding
                ? 'bg-green-500 text-white cursor-not-allowed'
                : 'bg-red-500 hover:bg-red-600 text-white hover:shadow-md active:scale-95'
            }`}
          >
            {isAdding ? (
              <span className="flex items-center">
                <span className="mr-2">✓</span>
                Ajouté
              </span>
            ) : (
              <span className="flex items-center">
                <span className="mr-2">+</span>
                Ajouter
              </span>
            )}
          </button>
        </div>

        {/* Badge promotion (optionnel) */}
        {pizza.promotion && (
          <div className="absolute top-2 right-2">
            <span className="bg-yellow-400 text-yellow-800 text-xs font-bold px-2 py-1 rounded-full">
              PROMO
            </span>
          </div>
        )}
      </div>
    </div>
  );
};

export default PizzaCard;