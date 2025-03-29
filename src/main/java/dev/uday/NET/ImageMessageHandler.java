package dev.uday.NET;

public class ImageMessageHandler {
    public static void handleImageMessage(byte[] packet) {
        byte messageType = packet[0];
        byte[] imageData = Arrays.copyOfRange(packet, 1, packet.length);

        switch (messageType) {
            // Handle general image
            case 2:
                handleGeneralImage(imageData);
                break;

            // Handle private image
            case 3:
                handlePrivateImage(imageData);
                break;
        }
    }

    private static void handleGeneralImage(byte[] imageData) {
        saveImage(imageData, "general_image.jpg");
        SwingUtilities.invokeLater(() -> ChatPanel.receiveImage("General", "general_image.jpg", false));
        System.out.println("Received a general image.");
    }

    private static void handlePrivateImage(byte[] imageData) {
        saveImage(imageData, "private_image.jpg");
        SwingUtilities.invokeLater(() -> ChatPanel.receiveImage("Private", "private_image.jpg", true));
        System.out.println("Received a private image.");
    }

    private static void saveImage(byte[] imageData, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(imageData);
            System.out.println("Image saved as " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
