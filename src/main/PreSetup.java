package main;

import tile.*;
import tileentity.*;
/**
 * 
 * @author Lucas Rezac
 *
 */
public class PreSetup {
	public static void init(){
		//register tiles
		TileRegistry.registerTile(new BlackTile());
		TileRegistry.registerTile(new BlackTileBottom());
		TileRegistry.registerTile(new BlackTileCracked());
		TileRegistry.registerTile(new BlackTileExposed());
		TileRegistry.registerTile(new BlackTileFrame());
		TileRegistry.registerTile(new BlackTileLight_LB());
		TileRegistry.registerTile(new BlackTileLight_LT());
		TileRegistry.registerTile(new BlackTileLight_RB());
		TileRegistry.registerTile(new BlackTileLight_RT());
		TileRegistry.registerTile(new BlackTileSmall());
		TileRegistry.registerTile(new BlackTileSmallStained());
		TileRegistry.registerTile(new BlackTileStained());
		TileRegistry.registerTile(new BlackTileTop());
		TileRegistry.registerTile(new DecorationGrass_B());
		TileRegistry.registerTile(new DecorationGrass_L());
		TileRegistry.registerTile(new DecorationGrass_R());
		TileRegistry.registerTile(new DecorationGrate_LB());
		TileRegistry.registerTile(new DecorationGrate_LT());
		TileRegistry.registerTile(new DecorationGrate_RB());
		TileRegistry.registerTile(new DecorationGrate_RT());
		TileRegistry.registerTile(new DecorationRubble());
		TileRegistry.registerTile(new DecorationSignArrow_B());
		TileRegistry.registerTile(new DecorationSignArrow_L());
		TileRegistry.registerTile(new DecorationSignArrow_R());
		TileRegistry.registerTile(new DecorationSignArrow_T());
		TileRegistry.registerTile(new DecorationSignCircle());
		TileRegistry.registerTile(new DecorationSignExit());
		TileRegistry.registerTile(new DecorationSignFive());
		TileRegistry.registerTile(new DecorationSignFour());
		TileRegistry.registerTile(new DecorationSignThree());
		TileRegistry.registerTile(new DecorationSignTwo());
		TileRegistry.registerTile(new DecorationSignTriangle());
		TileRegistry.registerTile(new DecorationVinesBottom());
		TileRegistry.registerTile(new DecorationVinesTop());
		TileRegistry.registerTile(new Door_L());
		TileRegistry.registerTile(new Door_R());
		TileRegistry.registerTile(new ExitDoor_L());
		TileRegistry.registerTile(new ExitDoor_R());
		TileRegistry.registerTile(new TileFloorButton_B());
		TileRegistry.registerTile(new TileFloorButton_L());
		TileRegistry.registerTile(new TileFloorButton_R());
		TileRegistry.registerTile(new TileFloorButton_T());
		TileRegistry.registerTile(new WhiteTile_B());
		TileRegistry.registerTile(new WhiteTile_L());
		TileRegistry.registerTile(new WhiteTile_LB());
		TileRegistry.registerTile(new WhiteTile_LR());
		TileRegistry.registerTile(new WhiteTile_LRB());
		TileRegistry.registerTile(new WhiteTile_LT());
		TileRegistry.registerTile(new WhiteTile_LTB());
		TileRegistry.registerTile(new WhiteTile_R());
		TileRegistry.registerTile(new WhiteTile_RB());
		TileRegistry.registerTile(new WhiteTile_RT());
		TileRegistry.registerTile(new WhiteTile_RTB());
		TileRegistry.registerTile(new WhiteTile_T());
		TileRegistry.registerTile(new WhiteTile_TB());
		TileRegistry.registerTile(new WhiteTile());
		TileRegistry.registerTile(new WhiteTileBottom());
		TileRegistry.registerTile(new WhiteTileLight_LB());
		TileRegistry.registerTile(new WhiteTileLight_LT());
		TileRegistry.registerTile(new WhiteTileLight_RB());
		TileRegistry.registerTile(new WhiteTileLight_RT());
		TileRegistry.registerTile(new WhiteTileMossy_LT());
		TileRegistry.registerTile(new WhiteTileMossy_RT());
		TileRegistry.registerTile(new WhiteTileMossy_T());
		TileRegistry.registerTile(new WhiteTileSmall());
		TileRegistry.registerTile(new WhiteTileStained_T());
		TileRegistry.registerTile(new WhiteTileStained_TB());
		TileRegistry.registerTile(new WhiteTileStainedBottom());
		TileRegistry.registerTile(new WhiteTileStainedTop());
		TileRegistry.registerTile(new WhiteTileTop());
		
		//register tile entities
		TileEntityRegistry.registerTileEntity(new TileEntityFloorButton_B());
		TileEntityRegistry.registerTileEntity(new TileEntityFloorButton_T());
		TileEntityRegistry.registerTileEntity(new TileEntityFloorButton_R());
		TileEntityRegistry.registerTileEntity(new TileEntityFloorButton_L());
	}
}
