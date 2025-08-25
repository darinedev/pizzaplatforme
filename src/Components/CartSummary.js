import React from 'react';
import { useCart } from '../context/CartContext';
import { formatPrice } from '../utils/validation';

const CartSummary = ({ items, total, showActions = true }) => {
  const { updateQuantity, removeFromCart } = useCart();

  const CartItem = ({ item, index }) => (
    <div className="flex items-center justify-between py-3 border-b border-gray-200 last:border-b-0">
      {/* Infos pizza */}
      <div className="flex-1">
        <h4 className="font-semibold text-gray-800">{item.nom}</h4>
        <p className="text-sm text-gray-600">
          {formatPrice(item.prix)} × {item.quantite}
        </p>
        {item.ingredients && (
          <p className="text-xs text-gray-500 mt-1">
            {item.ingredients.slice(0, 3).join(', ')}
            {item.ingredients.length > 3 && '...'}
          </p>
        )}
      </div>

      {/* Prix total item */}
      <div className="text-right ml-4">
        <p className="font-bold text-lg">
          {formatPrice(item.prix * item.quantite)}
        </p>
        
        {/* Actions quantité */}
        {showActions && (
          <div className="flex items-center mt-2 space-x-2">
            <button
              onClick={() => updateQuantity(item.id, item.quantite - 1)}
              className="w-8 h-8 rounded-full bg-gray-200 hover:bg-gray-300 flex items-center justify-center text-gray-700 transition-colors"
              aria-label="Diminuer quantité"
            >
              −
            </button>
            
            <span className="w-8 text-center font-semibold">
              {item.quantite}
            </span>
            
            <button
              onClick={() => updateQuantity(item.id, item.quantite + 1)}
              className="w-8 h-8 rounded-full bg-gray-200 hover:bg-gray-300 flex items-center justify-center text-gray-700 transition-colors"
              aria-label="Augmenter quantité"
            >
              +
            </button>
            
            <button
              onClick={() => removeFromCart(item.id)}
              className="ml-2 text-red-500 hover:text-red-700 transition-colors"
              aria-label="Supprimer du panier"
            >
              🗑️
            </button>
          </div>
        )}
      </div>
    </div>
  );

  if (!items || items.length === 0) {
    return (
      <div className="bg-white p-6 rounded-lg shadow-md text-center">
        <div className="text-6xl mb-4">🛒</div>
        <h2 className="text-xl font-semibold text-gray-800 mb-2">Panier vide</h2>
        <p className="text-gray-600">Ajoutez des pizzas pour commencer</p>
      </div>
    );
  }

  const deliveryFee = total >= 20 ? 0 : 3;
  const finalTotal = total + deliveryFee;

  return (
    <div className="bg-white p-6 rounded-lg shadow-md">
      {/* Header */}
      <h2 className="text-2xl font-bold text-gray-800 mb-4 flex items-center">
        <span className="mr-2">🛒</span>
        Votre commande ({items.length})
      </h2>

      {/* Items du panier */}
      <div className="space-y-2 mb-6">
        {items.map((item, index) => (
          <CartItem key={item.id || index} item={item} index={index} />
        ))}
      </div>

      {/* Calculs des totaux */}
      <div className="border-t border-gray-300 pt-4">
        <div className="space-y-2">
          {/* Sous-total */}
          <div className="flex justify-between text-gray-700">
            <span>Sous-total :</span>
            <span>{formatPrice(total)}</span>
          </div>
          
          {/* Frais de livraison */}
          <div className="flex justify-between text-gray-700">
            <span>Livraison :</span>
            <span>
              {deliveryFee === 0 ? (
                <span className="text-green-600 font-semibold">Gratuite !</span>
              ) : (
                formatPrice(deliveryFee)
              )}
            </span>
          </div>
          
          {/* Message minimum commande */}
          {total < 20 && (
            <div className="text-sm text-orange-600 bg-orange-50 p-2 rounded">
              <span className="mr-1">💡</span>
              Ajoutez {formatPrice(20 - total)} pour la livraison gratuite
            </div>
          )}
          
          {/* Total final */}
          <div className="flex justify-between text-xl font-bold text-gray-800 pt-2 border-t">
            <span>Total :</span>
            <span className="text-green-600">{formatPrice(finalTotal)}</span>
          </div>
        </div>
      </div>

      {/* Informations supplémentaires */}
      {!showActions && (
        <div className="mt-4 p-3 bg-blue-50 rounded-lg">
          <p className="text-sm text-blue-700">
            <span className="mr-1">📍</span>
            Livraison estimée sous 30-45 minutes
          </p>
        </div>
      )}
    </div>
  );
};

export default CartSummary;