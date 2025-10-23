import React, { useState, useEffect } from 'react';
import { api } from '../services/api.js';

const CartPage = () => {
    const [cart, setCart] = useState(null);
    const userId = localStorage.getItem('userId');

    useEffect(() => {
        const fetchCart = async () => {
            if (!userId) return;
            try {
                const response = await api.get(`/cart?userId=${userId}`);
                setCart(response);
            } catch (error) {
                console.error('Error fetching cart:', error);
                alert('Error fetching cart');
            }
        };

        fetchCart();
    }, [userId]);

    const removeFromCart = async (productId) => {
        try {
            const response = await api.delete(`/cart/items/${productId}?userId=${userId}`);
            setCart(response);
            alert('Item removed from cart');
        } catch (error) {
            console.error('Error removing from cart:', error);
            alert('Error removing from cart');
        }
    };

    const checkout = async () => {
        try {
            await api.post('/orders', { userId });
            alert('Order created successfully');
            setCart(null); // Clear the cart after checkout
        } catch (error) {
            console.error('Error creating order:', error);
            alert('Error creating order');
        }
    };

    if (!cart) {
        return <div>Your cart is empty.</div>;
    }

    return (
        <div>
            <h1>Your Cart</h1>
            {cart.items.map((item) => (
                <div key={item.productId}>
                    <p>Product ID: {item.productId}</p>
                    <p>Quantity: {item.quantity}</p>
                    <button onClick={() => removeFromCart(item.productId)}>Remove</button>
                </div>
            ))}
            <button onClick={checkout}>Checkout</button>
        </div>
    );
};

export default CartPage;
