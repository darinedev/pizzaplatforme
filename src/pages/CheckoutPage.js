import React from 'react';
import CartItem from '../components/CartItem';
import OrderForm from '../components/OrderForm';

const CheckoutPage = ({ 
  cartItems,
  onUpdateQuantity,
  onRemoveFromCart,
  getTotalPrice,
  getTotalItems,
  onNavigateToMenu,
  formData,
  formErrors,
  onFormChange,
  onSubmitOrder,
  orderLoading,
  error
}) => {
  const subtotal = getTotalPrice();
  const deliveryFee = subtotal >= 20 ? 0 : 3;
  const total = subtotal + deliveryFee;

  if (cartItems.length === 0) {
    return (
      <div className="container mx-auto px-4 py-8">
        <div className="max-w-2xl mx-auto">
          <h1 className="text-4xl font-bold text-gray-800 mb-8 text-center">Votre Panier</h1>
          
          <div className="text-center py-16">
            <div className="text-8xl mb-6">🛒</div>
            <h2 className="text-3xl font-bold text-gray-800 mb-4">Votre panier est vide</h2>
            <p className="text-xl text-gray-600 mb-8">
              Il est temps de découvrir nos délicieuses pizzas !
            </p>
            <button 
              onClick={onNavigateToMenu}
              className="bg-red-500 text-white px-8 py-4 rounded-xl text-lg hover:bg-red-600 transform hover:scale-105 transition-all duration-200 shadow-lg"
            >
              <span className="mr-2">🍕</span>
              Découvrir notre menu
            </button>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="max-w-7xl mx-auto">
        <div className="text-center mb-8">
          <h1 className="text-4xl font-bold text-gray-800 mb-2">Votre Panier</h1>
          <p className="text-gray-600">
            {getTotalItems()} article{getTotalItems() > 1 ? 's' : ''} • {subtotal}€
          </p>
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          {/* Articles du panier */}
          <div className="lg:col-span-2">
            <div className="bg-white rounded-2xl shadow-lg overflow-hidden">
              <div className="bg-gray-50 px-6 py-4 border-b">
                <h2 className="text-xl font-semibold text-gray-800">Vos pizzas</h2>
              </div>
              
              <div className="divide-y divide-gray-100">
                {cartItems.map((item, index) => (
                  <CartItem
                    key={item.id}
                    item={item}
                    onUpdateQuantity={onUpdateQuantity}
                    onRemove={onRemoveFromCart}
                    isLast={index === cartItems.length - 1}
                  />
                ))}
              </div>
              
              {/* Actions du panier */}
              <div className="p-6 bg-gray-50 border-t">
                <button
                  onClick={onNavigateToMenu}
                  className="text-red-500 hover:text-red-600 font-medium transition-colors"
                >
                  ← Ajouter d'autres pizzas
                </button>
              </div>
            </div>
          </div>

          {/* Résumé et formulaire */}
          <div className="space-y-6">
            {/* Résumé de commande */}
            <div className="bg-white rounded-2xl shadow-lg overflow-hidden">
              <div className="bg-gray-50 px-6 py-4 border-b">
                <h2 className="text-xl font-semibold text-gray-800">Résumé</h2>
              </div>
              
              <div className="p-6">
                <div className="space-y-4">
                  <div className="flex justify-between text-gray-600">
                    <span>Sous-total ({getTotalItems()} article{getTotalItems() > 1 ? 's' : ''})</span>
                    <span>{subtotal.toFixed(2)}€</span>
                  </div>
                  
                  <div className="flex justify-between text-gray-600">
                    <span>Frais de livraison</span>
                    <span className={deliveryFee === 0 ? 'text-green-600 font-medium' : ''}>
                      {deliveryFee === 0 ? 'Gratuit' : `${deliveryFee.toFixed(2)}€`}
                    </span>
                  </div>
                  
                  {subtotal < 20 && (
                    <div className="bg-yellow-50 border border-yellow-200 rounded-lg p-3">
                      <p className="text-sm text-yellow-800">
                        <span className="font-medium">💡 Astuce :</span> Ajoutez {(20 - subtotal).toFixed(2)}€ 
                        pour bénéficier de la livraison gratuite !
                      </p>
                    </div>
                  )}
                  
                  <div className="border-t pt-4">
                    <div className="flex justify-between text-xl font-bold">
                      <span>Total</span>
                      <span className="text-red-600">{total.toFixed(2)}€</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            {/* Formulaire de commande */}
            <OrderForm
              formData={formData}
              formErrors={formErrors}
              onFormChange={onFormChange}
              onSubmit={onSubmitOrder}
              loading={orderLoading}
              error={error}
              total={total}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default CheckoutPage;